package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadQualityCountAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long badCount = sessions.stream()
                .filter(session -> session.getQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult<>(
                "Количество сессий с плохим качеством сна",
                badCount
        );
    }
}
