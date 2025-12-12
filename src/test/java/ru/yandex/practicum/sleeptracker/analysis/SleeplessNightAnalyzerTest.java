package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightAnalyzerTest {

    @Test
    void shouldCountNightWithoutAnySessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 22, 0),
                        LocalDateTime.of(2025, 10, 4, 6, 0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightAnalyzer analyzer = new SleeplessNightAnalyzer();
        SleepAnalysisResult<Long> result = analyzer.apply(sessions);

        assertEquals(1, result.getValue());
    }

    @Test
    void daySleepDoesNotPreventSleeplessNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 14, 0),
                        LocalDateTime.of(2025, 10, 2, 15, 0),
                        SleepQuality.NORMAL
                )
        );

        SleeplessNightAnalyzer analyzer = new SleeplessNightAnalyzer();
        assertEquals(1, analyzer.apply(sessions).getValue());
    }

    @Test
    void nightSleepPreventsSleeplessNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 2, 0),
                        LocalDateTime.of(2025, 10, 2, 5, 0),
                        SleepQuality.NORMAL
                )
        );

        SleeplessNightAnalyzer analyzer = new SleeplessNightAnalyzer();
        assertEquals(0, analyzer.apply(sessions).getValue());
    }

    @Test
    void emptySessionsHaveNoSleeplessNights() {
        SleeplessNightAnalyzer analyzer = new SleeplessNightAnalyzer();
        assertEquals(0, analyzer.apply(List.of()).getValue());
    }
}
