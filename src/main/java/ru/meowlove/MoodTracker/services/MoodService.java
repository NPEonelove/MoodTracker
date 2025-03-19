package ru.meowlove.MoodTracker.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.EditMoodDTO;
import ru.meowlove.MoodTracker.dto.mood.GetMoodDTO;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotAddedException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotDeletedException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotHavePermissionsForEditException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotHavePermissionsForGiveException;
import ru.meowlove.MoodTracker.models.Account;
import ru.meowlove.MoodTracker.models.Mood;
import ru.meowlove.MoodTracker.repositories.AccountRepository;
import ru.meowlove.MoodTracker.repositories.MoodRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

//        mood.setAccount(accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null));
        mood.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (mood.getAccount() != null) {
            if (moodRepository.existsByAccountUsernameAndDate(mood.getAccount().getUsername(), mood.getDate())) {
                throw new MoodNotAddedException("Mood already exists");
            }
        }
        moodRepository.save(mood);
    }

    public void editMood(int id, EditMoodDTO editMoodDTO) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
        Account account = accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null);

//        Account account = accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null);
        if (mood.getAccount() == account) {
            mood = modelMapper.map(editMoodDTO, Mood.class);
            mood.setAccount(account);
            mood.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            mood.setId(id);
            moodRepository.save(mood);
            return;
        }
        throw new MoodNotHavePermissionsForEditException("You do not have permissions to edit mood");
    }

    public GetMoodDTO getMood(int id) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
//        if (mood.getAccount() == accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null)) {
        if (mood.getAccount() == accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null)) {
            return modelMapper.map(mood, GetMoodDTO.class);
        }
        throw new MoodNotHavePermissionsForGiveException("You do not have permissions to view mood");
    }

    public void deleteMood(int id) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
        if (mood.getAccount() == accountRepository.findByUsername(accountService.getCurrentUser().getUsername()).orElse(null)) {
            moodRepository.deleteMoodById(id);
            return;
        }
        throw new MoodNotDeletedException("You do not have permissions to delete mood");
    }
}
