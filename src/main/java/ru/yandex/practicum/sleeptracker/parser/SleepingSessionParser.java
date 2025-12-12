package ru.yandex.practicum.sleeptracker.parser;

import ru.yandex.practicum.sleeptracker.sleepingsession.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSessionParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");


    public SleepingSession parse(String line) {
        String[] parts = line.split(";");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки лога сна: " + line);
        }

        LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);
        LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(parts[2]);

        return new SleepingSession(start, end, quality);
    }
}
