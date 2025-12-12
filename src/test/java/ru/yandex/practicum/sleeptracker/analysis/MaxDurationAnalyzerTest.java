package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxDurationAnalyzerTest {

    @Test
    void shouldFindMaxDuration() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 14, 0),
                        LocalDateTime.of(2025, 10, 2, 15, 0),
                        SleepQuality.NORMAL
                )
        );

        MaxDurationAnalyzer analyzer = new MaxDurationAnalyzer();
        SleepAnalysisResult<Long> result = analyzer.apply(sessions);

        assertEquals(480, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        MaxDurationAnalyzer analyzer = new MaxDurationAnalyzer();
        assertEquals(0, analyzer.apply(List.of()).getValue());
    }
}
