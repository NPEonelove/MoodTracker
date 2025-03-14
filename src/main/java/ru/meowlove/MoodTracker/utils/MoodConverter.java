//package ru.meowlove.MoodTracker.utils;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.meowlove.MoodTracker.models.Mood;
//
//@Component
//public class MoodConverter {
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public MoodConverter(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    public Mood convertToMood(MoodDTO moodDTO) {
//        return modelMapper.map(moodDTO, Mood.class);
//    }
//
//    public MoodDTO convertToMoodDTO(Mood mood) {
//        return modelMapper.map(mood, MoodDTO.class);
//    }
//}
