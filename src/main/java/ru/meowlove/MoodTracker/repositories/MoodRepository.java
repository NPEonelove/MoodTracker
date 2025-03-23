package ru.meowlove.MoodTracker.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.meowlove.MoodTracker.models.Mood;

import java.util.Date;
import java.util.List;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Integer> {

    boolean existsByAccountUsernameAndDate(String accountUsername, Date date);

    Mood findByAccountUsernameAndDate(String accountUsername, Date date);
    void deleteMoodById(int id);
    List<Mood> findByAccountUsername(String accountUsername);
    List<Mood> findByAccountUsername(String accountUsername, Pageable pageable);

}
