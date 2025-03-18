package ru.meowlove.MoodTracker.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
import ru.meowlove.MoodTracker.services.AccountService;
import ru.meowlove.MoodTracker.services.MoodService;

import java.util.List;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/mood")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;
    private final MoodRepository moodRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AddMoodDTO> addMood(@RequestBody AddMoodDTO addMoodDTO) {
        moodService.addMood(addMoodDTO);
        return new ResponseEntity<>(addMoodDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMoodDTO> getMood(@PathVariable("id") int id) {
        return new ResponseEntity<>(moodService.getMood(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EditMoodDTO> editMood(@PathVariable("id") int id, @RequestBody EditMoodDTO editMoodDTO) {
        moodService.editMood(id, editMoodDTO);
        return new ResponseEntity<>(editMoodDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable("id") int id) {
        moodService.deleteMood(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<GetMoodDTO>> getMoodHistory(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(moodRepository
                .findByAccountUsername(
                        accountService.getCurrentUser().getUsername(), pageable)
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList());
    }
}
