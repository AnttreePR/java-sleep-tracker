package ru.yandex.practicum.sleeptracker.exceptions;

public class FileReadingError extends RuntimeException {
    public FileReadingError(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadingError(String message) {
        super(message);
    }
}
