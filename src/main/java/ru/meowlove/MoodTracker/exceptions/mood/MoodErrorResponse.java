package ru.meowlove.MoodTracker.exceptions.mood;

import java.util.Date;

public class MoodErrorResponse {

    private String errorMessage;
    private Date timestamp;


    public MoodErrorResponse(String errorMessage, Date timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
