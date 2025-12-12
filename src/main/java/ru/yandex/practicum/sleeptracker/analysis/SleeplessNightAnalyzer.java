package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

public class SleeplessNightAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>(
                    "Количество бессонных ночей",
                    0L
            );
        }

        LocalDateTime firstStart = sessions.stream()
                .map(SleepingSession::getStart)
                .min(LocalDateTime::compareTo)
                .orElseThrow();

        LocalDateTime lastEnd = sessions.stream()
                .map(SleepingSession::getEnd)
                .max(LocalDateTime::compareTo)
                .orElseThrow();

        LocalDate firstNight = firstStart.toLocalTime().isBefore(LocalTime.NOON)
                ? firstStart.toLocalDate().minusDays(1)
                : firstStart.toLocalDate();

        LocalDate lastNight = lastEnd.toLocalTime().isBefore(LocalTime.NOON)
                ? lastEnd.toLocalDate().minusDays(1)
                : lastEnd.toLocalDate();

        long insomniaCount = LongStream.rangeClosed(
                        firstNight.toEpochDay(),
                        lastNight.toEpochDay()
                )
                .mapToObj(LocalDate::ofEpochDay)
                .filter(nightDate -> isSleeplessNight(nightDate, sessions))
                .count();

        return new SleepAnalysisResult<>(
                "Количество бессонных ночей",
                insomniaCount
        );
    }

    private boolean isSleeplessNight(LocalDate nightDate, List<SleepingSession> sessions) {

        LocalDateTime nightStart = nightDate.atTime(0, 0);
        LocalDateTime nightEnd = nightDate.atTime(6, 0);

        return sessions.stream()
                .noneMatch(session ->
                        session.getStart().isBefore(nightEnd)
                                && session.getEnd().isAfter(nightStart)
                );
    }
}
