package ru.meowlove.MoodTracker.dto.mood;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditMoodDTO {

    @Min(value = 1, message = "Значение настроения должно быть от 1 до 10")
    @Max(value = 100, message = "Значение настроения должно быть от 1 до 10")
    @Schema(example = "69")
    private Integer value;

    @Size(max = 65536, message = "Максимальный размер комментария - 65536 символов")
    @Schema(example = "So good <3")
    private String comment;

}
