package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class TotalSessionsAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Общее количество сессий сна",
                sessions.size()
        );
    }
}
