package ru.yandex.practicum.sleeptracker.sleepingsession;

import ru.yandex.practicum.sleeptracker.exceptions.SleepStartLaterEnd;

import java.time.Duration;
import java.time.LocalDate;
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
        return intersectsNightInterval(start.toLocalDate())
                || intersectsNightInterval(end.toLocalDate());
    }


    public boolean intersectsNightInterval(LocalDate nightDate) {
        LocalDateTime nightStart = nightDate.atTime(0, 0);
        LocalDateTime nightEnd = nightDate.atTime(6, 0);
        return start.isBefore(nightEnd) && end.isAfter(nightStart);
    }

    public boolean isNightSession() {
        return intersectsNightInterval();
    }


}
