package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class MaxDurationAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        Long maxDuration = sessions.stream()
                .mapToLong(SleepingSession::getDurationSleep)
                .max()
                .orElse(0L);

        return new SleepAnalysisResult<>(
                "Минимальная продолжительность сессии (мин)",
                maxDuration
        );
    }

}
