package ru.meowlove.MoodTracker.services;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meowlove.MoodTracker.dto.mood.AddMoodDTO;
import ru.meowlove.MoodTracker.models.Mood;
import ru.meowlove.MoodTracker.repositories.AccountRepository;
import ru.meowlove.MoodTracker.repositories.MoodRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
        mood.setDate(new Date());
        mood.setAccount(accountRepository.findByUsername(String.valueOf(session.getAttribute("accountUsername"))).orElse(null));
        moodRepository.save(mood);
    }

//    public boolean checkTodayMoodExists(HttpSession session) {
//        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
////        Mood mood = moodRepository.findByDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())).orElse(null);
//
//        if (mood == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
