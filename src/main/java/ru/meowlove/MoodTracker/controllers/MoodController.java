package ru.meowlove.MoodTracker.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetHistoryMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.services.MoodService;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/mood")
@RequiredArgsConstructor
@Tag(name = "MoodController", description = "Контроллер для работы с настроением. \nНа вход в headers всегда отдавать jwt токен текущего пользователя")
public class MoodController {

    private final MoodService moodService;

    @Operation(
            summary = "Добавление сегодняшнего настроения"
    )
    @PostMapping
    public ResponseEntity<AddMoodDTO> addMood(@RequestBody AddMoodDTO addMoodDTO) {
        moodService.addMood(addMoodDTO);
        return new ResponseEntity<>(addMoodDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение настроения по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GetMoodDTO> getMood(@PathVariable("id") int id) {
        return new ResponseEntity<>(moodService.getMood(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление настроения по id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable("id") int id) {
        moodService.deleteMood(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение истории настроения"
    )
    @GetMapping("/history")
    public ResponseEntity<GetHistoryMoodDTO> getMoodHistory() {
        return ResponseEntity.ok(moodService.getHistoryMood());
    }
}
