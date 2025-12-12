package ru.yandex.practicum.sleeptracker.sleepingsession;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SleepingSessionTest {

    @Test
    void nightSleepCrossesMidnightTrue() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 3, 0),
                SleepQuality.GOOD
        );

        assertTrue(session.intersectsNightInterval());
    }

    @Test
    void sleepInsideNightTrue() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 1, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.NORMAL
        );

        assertTrue(session.intersectsNightInterval());
    }

    @Test
    void sleepAfterSixFalse() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 7, 0),
                LocalDateTime.of(2025, 10, 2, 10, 0),
                SleepQuality.GOOD
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleepBeforeMidnightFalse() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 0),
                LocalDateTime.of(2025, 10, 1, 23, 0),
                SleepQuality.BAD
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleepEndsAtMidnightFalse() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 0, 0),
                SleepQuality.NORMAL
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleepStartsAtSixFalse() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 6, 0),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD
        );

        assertFalse(session.intersectsNightInterval());
    }
}
