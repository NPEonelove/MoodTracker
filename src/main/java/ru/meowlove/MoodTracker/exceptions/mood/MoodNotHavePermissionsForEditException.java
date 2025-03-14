package ru.meowlove.MoodTracker.exceptions.mood;

public class MoodNotHavePermissionsForEditException extends RuntimeException {
    public MoodNotHavePermissionsForEditException(String message) {
        super(message);
    }
}
