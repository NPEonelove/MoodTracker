package ru.meowlove.MoodTracker.services;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MoodService {

    private final MoodRepository moodRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MoodService(MoodRepository moodRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.moodRepository = moodRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public void addMood(AddMoodDTO addMoodDTO, HttpSession session) {
        Mood mood = modelMapper.map(addMoodDTO, Mood.class);
        mood.setAccount(accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null));
        mood.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (mood.getAccount() != null) {
            if (moodRepository.existsByAccountUsernameAndDate(mood.getAccount().getUsername(), mood.getDate())) {
                throw new MoodNotAddedException("Mood already exists");
            }
        }
        moodRepository.save(mood);
    }

    public void editMood(int id, EditMoodDTO editMoodDTO, HttpSession session) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
        Account account = accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null);
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

    public GetMoodDTO getMood(int id, HttpSession session) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
        if (mood.getAccount() == accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null)) {
            return modelMapper.map(mood, GetMoodDTO.class);
        }
        throw new MoodNotHavePermissionsForGiveException("You do not have permissions to view mood");
    }

    public void deleteMood(int id, HttpSession session) {
        Mood mood = moodRepository.findById(id).orElseThrow(() -> new RuntimeException("Mood not found"));
        if (mood.getAccount() == accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null)) {
            moodRepository.deleteMoodById(id);
            return;
        }
        throw new MoodNotDeletedException("You do not have permissions to delete mood");
    }

    public List<GetMoodDTO> getAllMoods(HttpSession session) {
        List<Mood> moods = moodRepository.findByAccountUsername(String.valueOf(session.getAttribute("accountUsername")));
        List<GetMoodDTO> dtos = new ArrayList<>();
        for (Mood mood : moods) {
            dtos.add(modelMapper.map(mood, GetMoodDTO.class));
        }
        return dtos;
    }

}
