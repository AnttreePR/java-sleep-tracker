package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepQuality;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChronotypeAnalyzerTest {

    @Test
    void shouldDetectOwl() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalyzer analyzer = new ChronotypeAnalyzer();
        SleepAnalysisResult<Chronotype> result = analyzer.apply(sessions);

        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void shouldDetectLark() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 30),
                        LocalDateTime.of(2025, 10, 2, 6, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalyzer analyzer = new ChronotypeAnalyzer();
        assertEquals(Chronotype.LARK, analyzer.apply(sessions).getValue());
    }

    @Test
    void tieResultsInDove() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 30),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeAnalyzer analyzer = new ChronotypeAnalyzer();
        assertEquals(Chronotype.DOVE, analyzer.apply(sessions).getValue());
    }
}
