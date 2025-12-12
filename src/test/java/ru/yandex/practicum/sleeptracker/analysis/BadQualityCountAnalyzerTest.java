package ru.yandex.practicum.sleeptracker.analysis;


import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadQualityCountAnalyzerTest {

    @Test
    void shouldCountBadQualitySessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        SleepQuality.GOOD
                )
        );

        BadQualityCountAnalyzer analyzer = new BadQualityCountAnalyzer();
        SleepAnalysisResult<Long> result = analyzer.apply(sessions);

        assertEquals(1, result.getValue());
    }

    @Test
    void shouldReturnZeroIfNoBadSessions() {
        BadQualityCountAnalyzer analyzer = new BadQualityCountAnalyzer();
        assertEquals(0, analyzer.apply(List.of()).getValue());
    }
}


