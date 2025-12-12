package ru.yandex.practicum.sleeptracker.exceptions;


public class SleepStartLaterEnd extends RuntimeException {
    public SleepStartLaterEnd(String message, Throwable cause) {
        super(message, cause);
    }

    public SleepStartLaterEnd(String message) {
        super(message);
    }
}
