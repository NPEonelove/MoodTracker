package ru.meowlove.MoodTracker.dto.mood;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class GetMoodDTO {


    private int id;

    @Schema(example = "69")
    private int value;

    @Schema(example = "So good <3")
    private String comment;

    private Date date;

}
