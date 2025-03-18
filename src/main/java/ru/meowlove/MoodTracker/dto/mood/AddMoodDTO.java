package ru.meowlove.MoodTracker.dto.mood;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMoodDTO {

    @Min(value = 1, message = "Значение настроения должно быть от 1 до 10")
    @Max(value = 10, message = "Значение настроения должно быть от 1 до 10")
    private Integer value;

    @Size(max = 65536, message = "Максимальный размер комментария - 65536 символов")
    private String comment;

}
