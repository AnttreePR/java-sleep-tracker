package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSessionsAnalyzerTest {

    @Test
    void shouldCountAllSessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        SleepQuality.NORMAL
                )
        );

        TotalSessionsAnalyzer analyzer = new TotalSessionsAnalyzer();
        SleepAnalysisResult<Integer> result = analyzer.apply(sessions);

        assertEquals(2, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        TotalSessionsAnalyzer analyzer = new TotalSessionsAnalyzer();
        SleepAnalysisResult<Integer> result = analyzer.apply(List.of());

        assertEquals(0, result.getValue());
    }
}
