package ru.yandex.practicum.sleeptracker.sleepingsession;

import ru.yandex.practicum.sleeptracker.exceptions.SleepStartLaterEnd;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {

        if (end.isBefore(start)) {
            throw new SleepStartLaterEnd("Время окончания сна раньше времени начала");
        }

        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationSleep() {
        return Duration.between(start, end).toMinutes();
    }

    public boolean intersectsNightInterval() {
        LocalDateTime nightStart = end.toLocalDate().atTime(0, 0);
        LocalDateTime nightEnd = end.toLocalDate().atTime(6, 0);

        boolean intersects = start.isBefore(nightEnd) && end.isAfter(nightStart);

        return intersects;
    }

    public boolean isNightSession() {
        return intersectsNightInterval();
    }

}
