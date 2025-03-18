package ru.meowlove.MoodTracker.dto.mood;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class GetMoodDTO {

    private int id;

    private int value;

    private String comment;

    private Date date;

}
