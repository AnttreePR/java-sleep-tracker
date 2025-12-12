package ru.yandex.practicum.sleeptracker.exceptions;

public class InvalidStringFormat extends RuntimeException {
    public InvalidStringFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStringFormat(String message) {
        super(message);
    }
}
