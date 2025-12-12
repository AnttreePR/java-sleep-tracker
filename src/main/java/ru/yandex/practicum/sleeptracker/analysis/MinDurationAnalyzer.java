package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MinDurationAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        Long minDuration = sessions.stream()
                .mapToLong(SleepingSession::getDurationSleep)
                .min()
                .orElse(0L);

        return new SleepAnalysisResult<>(
                "Минимальная продолжительность сессии (мин)",
                minDuration
        );
    }

}
