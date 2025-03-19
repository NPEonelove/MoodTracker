package ru.meowlove.MoodTracker.exceptions.mood;

public class MoodNotFoundException extends RuntimeException {
    public MoodNotFoundException(String message) {
        super(message);
    }
}
