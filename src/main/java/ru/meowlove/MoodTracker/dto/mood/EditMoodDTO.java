package ru.meowlove.MoodTracker.dto.mood;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class EditMoodDTO {

    @Min(value = 1, message = "Значение настроения должно быть от 1 до 10")
    @Max(value = 10, message = "Значение настроения должно быть от 1 до 10")
    private Integer value;

    @Size(max = 65536, message = "Максимальный размер комментария - 65536 символов")
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
