package ru.meowlove.MoodTracker.exceptions.mood;

public class MoodNotDeletedException extends RuntimeException {
    public MoodNotDeletedException(String message) {
        super(message);
    }
}
