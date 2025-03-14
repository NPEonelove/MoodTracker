package ru.meowlove.MoodTracker.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.EditMoodDTO;
import ru.meowlove.MoodTracker.services.MoodService;

@RestController
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;

    @Autowired
    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping("/add-mood")
    public ResponseEntity<AddMoodDTO> addMood(@RequestBody AddMoodDTO addMoodDTO, HttpSession session) {
        moodService.addMood(addMoodDTO, session);
        return new ResponseEntity<>(addMoodDTO, HttpStatus.OK);
    }

//    @PatchMapping("/edit-mood")
//    public ResponseEntity<HttpStatus> editMood(@RequestBody EditMoodDTO editMoodDTO, HttpSession session) {
//
//    }

//    @GetMapping("/check-today-exists")
//    public ResponseEntity<Boolean> checkTodayMoodExists(HttpSession session) {
//        return new ResponseEntity<>(moodService.checkTodayMoodExists(session), HttpStatus.OK);
//    }
}
