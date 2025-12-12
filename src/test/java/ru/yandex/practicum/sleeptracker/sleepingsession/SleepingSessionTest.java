package ru.yandex.practicum.sleeptracker.sleepingsession;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SleepingSessionTest {

    @Test
    void nightSleep_crossesMidnight_true() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 2, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.GOOD
        );

        assertTrue(session.intersectsNightInterval());
    }

    @Test
    void sleep_insideNight_true() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 1, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.NORMAL
        );

        assertTrue(session.intersectsNightInterval());
    }

    @Test
    void sleep_afterSix_false() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 7, 0),
                LocalDateTime.of(2025, 10, 2, 10, 0),
                SleepQuality.GOOD
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleep_beforeMidnight_false() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 0),
                LocalDateTime.of(2025, 10, 1, 23, 0),
                SleepQuality.BAD
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleep_endsAtMidnight_false() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 0, 0),
                SleepQuality.NORMAL
        );

        assertFalse(session.intersectsNightInterval());
    }

    @Test
    void sleep_startsAtSix_false() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 6, 0),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD
        );

        assertFalse(session.intersectsNightInterval());
    }
}
