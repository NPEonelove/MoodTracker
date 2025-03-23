package ru.meowlove.MoodTracker.dto.mood;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetHistoryMoodDTO {

    private List<GetMoodDTO> history;

    private String current_average;
    private String average;
}
