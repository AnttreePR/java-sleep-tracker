package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AverageDurationAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Double>> {

    @Override
    public SleepAnalysisResult<Double> apply(List<SleepingSession> sessions) {
        Double averageDuration = sessions.stream()
                .mapToLong(SleepingSession::getDurationSleep)
                .average()
                .orElse(0.0);
        return new SleepAnalysisResult<>(
                "Средняя продолжительность сессии (мин)",
                averageDuration
        );
    }

}
