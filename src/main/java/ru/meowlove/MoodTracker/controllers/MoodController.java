package ru.meowlove.MoodTracker.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.EditMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.services.MoodService;

import java.util.List;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;

    @Autowired
    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping
    public ResponseEntity<AddMoodDTO> addMood(@RequestBody AddMoodDTO addMoodDTO, HttpSession session) {
        moodService.addMood(addMoodDTO, session);
        return new ResponseEntity<>(addMoodDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMoodDTO> getMood(@PathVariable("id") int id, HttpSession session) {
        return new ResponseEntity<>(moodService.getMood(id, session), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EditMoodDTO> editMood(@PathVariable("id") int id, @RequestBody EditMoodDTO editMoodDTO, HttpSession session) {
        moodService.editMood(id, editMoodDTO, session);
        return new ResponseEntity<>(editMoodDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable("id") int id, HttpSession session) {
        moodService.deleteMood(id, session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<GetMoodDTO>> getMoodHistory(HttpSession session) {
        return ResponseEntity.ok(moodService.getAllMoods(session));
    }
}
