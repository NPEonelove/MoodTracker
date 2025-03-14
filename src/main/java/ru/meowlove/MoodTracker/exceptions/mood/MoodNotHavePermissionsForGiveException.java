package ru.meowlove.MoodTracker.exceptions.mood;

public class MoodNotHavePermissionsForGiveException extends RuntimeException {
    public MoodNotHavePermissionsForGiveException(String message) {
        super(message);
    }
}
