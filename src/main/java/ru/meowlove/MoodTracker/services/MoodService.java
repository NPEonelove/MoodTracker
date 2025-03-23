package ru.meowlove.MoodTracker.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetHistoryMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotDeletedException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotFoundException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotHavePermissionsForGiveException;
import ru.meowlove.MoodTracker.models.Mood;
import ru.meowlove.MoodTracker.repositories.AccountRepository;
import ru.meowlove.MoodTracker.repositories.MoodRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoodService {

    private final MoodRepository moodRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;


    public void addMood(AddMoodDTO addMoodDTO) {
        Mood mood = modelMapper.map(addMoodDTO, Mood.class);
        mood.setAccount(accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null));
        mood.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (mood.getAccount() != null) {
            if (moodRepository.existsByAccountUsernameAndDate(mood.getAccount().getUsername(), mood.getDate())) {
                mood.setId(moodRepository.findByAccountUsernameAndDate(mood.getAccount().getUsername(), mood.getDate()).getId());
                moodRepository.save(mood);
            }
        }
        moodRepository.save(mood);
    }

    public GetMoodDTO getMood(int id) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new MoodNotFoundException("Mood not found"));
        if (mood.getAccount() == accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null)) {
            return modelMapper.map(mood, GetMoodDTO.class);
        }
        throw new MoodNotHavePermissionsForGiveException("You do not have permissions to view mood");
    }

    public void deleteMood(int id) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new MoodNotFoundException("Mood not found"));
        if (mood.getAccount() == accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null)) {
            moodRepository.deleteMoodById(id);
            return;
        }
        throw new MoodNotDeletedException("You do not have permissions to delete mood");
    }

    public GetHistoryMoodDTO getHistoryMood() {
        GetHistoryMoodDTO getHistoryMoodDTO = new GetHistoryMoodDTO();
        getHistoryMoodDTO.setHistory(moodRepository
                .findByAccountUsername(
                        accountService.getCurrentUser().getUsername())
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList());
        List<GetMoodDTO> current_moods = moodRepository
                .findByAccountUsername(
                        accountService.getCurrentUser().getUsername(), PageRequest.of(0, 7, Sort.by("date").descending()))
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList();
        int sum = 0;
        for (GetMoodDTO getMoodDTO : current_moods) {
            sum += getMoodDTO.getValue();
        }
        if (sum > 0) {
            getHistoryMoodDTO.setCurrent_average(String.format("%.2f", (double) sum / current_moods.stream().toList().size()));
        } else {
            getHistoryMoodDTO.setCurrent_average("0");
        }
        List<GetMoodDTO> moods = moodRepository
                .findByAccountUsername(
                        accountService.getCurrentUser().getUsername())
                .stream().map(mood -> modelMapper.map(mood, GetMoodDTO.class)).toList();
        sum = 0;
        for (GetMoodDTO moodDTO : moods) {
            sum += moodDTO.getValue();
        }
        if (sum > 0) {
            getHistoryMoodDTO.setAverage(String.format("%.2f", (double) sum / moods.stream().toList().size()));
        } else {
            getHistoryMoodDTO.setAverage("0");
        }
        return getHistoryMoodDTO;
    }
}
