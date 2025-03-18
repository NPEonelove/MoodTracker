package ru.meowlove.MoodTracker.controllers;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.EditMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.repositories.MoodRepository;
import ru.meowlove.MoodTracker.services.MoodService;

import java.util.List;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/mood")
public class MoodController {

    private final MoodService moodService;
    private final MoodRepository moodRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MoodController(MoodService moodService, MoodRepository moodRepository, ModelMapper modelMapper) {
        this.moodService = moodService;
        this.moodRepository = moodRepository;
        this.modelMapper = modelMapper;
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
    public ResponseEntity<List<GetMoodDTO>> getMoodHistory(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     HttpSession session) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(moodRepository
                .findByAccountUsername(
                        String.valueOf(session.getAttribute("accountUsername")), pageable)
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList());
    }
}
