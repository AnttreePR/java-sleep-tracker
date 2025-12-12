package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Chronotype>> {

    @Override
    public SleepAnalysisResult<Chronotype> apply(final List<SleepingSession> sessions) {
        Map<Chronotype, Long> counts = sessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(this::classifySession)
                .collect(Collectors.groupingBy(
                        chronotype -> chronotype,
                        Collectors.counting()
                ));

        long owls = counts.getOrDefault(Chronotype.OWL, 0L);
        long larks = counts.getOrDefault(Chronotype.LARK, 0L);
        long doves = counts.getOrDefault(Chronotype.DOVE, 0L);

        Chronotype result;

        // 2. Выбираем преобладающий тип
        if (owls > larks && owls > doves) {
            result = Chronotype.OWL;
        } else if (larks > owls && larks > doves) {
            result = Chronotype.LARK;
        } else {
            result = Chronotype.DOVE;
        }

        return new SleepAnalysisResult<>(
                "Хронотип пользователя",
                result
        );
    }

    private Chronotype classifySession(SleepingSession session) {

        LocalTime sleepTime = session.getStart().toLocalTime();
        LocalTime wakeTime = session.getEnd().toLocalTime();

        // Сова
        if (sleepTime.isAfter(LocalTime.of(23, 0))
                && wakeTime.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }

        // Жаворонок
        if (sleepTime.isBefore(LocalTime.of(22, 0))
                && wakeTime.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }

        // Все остальные случаи
        return Chronotype.DOVE;
    }
}