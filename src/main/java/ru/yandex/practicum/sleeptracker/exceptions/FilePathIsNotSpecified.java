package ru.yandex.practicum.sleeptracker.exceptions;

public class FilePathIsNotSpecified extends RuntimeException {
    public FilePathIsNotSpecified(String message, Throwable cause) {
        super(message, cause);
    }

    public FilePathIsNotSpecified(String message) {
        super(message);
    }
}
