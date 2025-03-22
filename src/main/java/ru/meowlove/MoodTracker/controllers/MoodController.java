package ru.meowlove.MoodTracker.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.repositories.MoodRepository;
import ru.meowlove.MoodTracker.services.AccountService;
import ru.meowlove.MoodTracker.services.MoodService;

import java.util.LinkedList;
import java.util.List;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/mood")
@RequiredArgsConstructor
@Tag(name = "MoodController", description = "Контроллер для работы с настроением. \nНа вход в headers всегда отдавать jwt токен текущего пользователя")
public class MoodController {

    private final MoodService moodService;
    private final MoodRepository moodRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

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
    public ResponseEntity<List<GetMoodDTO>> getMoodHistory(@RequestParam(defaultValue = "0")
                                                           @Parameter(description = "Какая по счету страница с данными (отсчет с 0). По умолчанию 0")
                                                           int page,
                                                           @RequestParam(defaultValue = "10")
                                                           @Parameter(description = "Количество настроений на одной странице. По умолчанию 10")
                                                           int size,
                                                           @RequestParam(defaultValue = "false")
                                                           @Parameter(description = "Сортировка. По умолчанию по возрастанию (тоесть для получения ласт недели надо включить ее, " +
                                                                   "чтобы была сортировка по убыванию, и к ней уже применить пагинацию")
                                                           boolean sort) {
        Pageable pageable = PageRequest.of(page, size);
        if (sort) {
            pageable = PageRequest.of(page, size, Sort.by("date").descending());
            LinkedList<GetMoodDTO> list = new LinkedList<>();
            list.addAll(moodRepository
                    .findByAccountUsername(
                            accountService.getCurrentUser().getUsername(), pageable)
                    .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList().reversed());
            return ResponseEntity.ok(list);
        }

        return ResponseEntity.ok(moodRepository
                .findByAccountUsername(
                        accountService.getCurrentUser().getUsername(), pageable)
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList());
    }
}
