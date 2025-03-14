package ru.meowlove.MoodTracker.dto.mood;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AddMoodDTO {



    @Min(value = 1)
    @Max(value = 10)
    private Integer value;

    @Size(max = 65536)
    private String comment;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
