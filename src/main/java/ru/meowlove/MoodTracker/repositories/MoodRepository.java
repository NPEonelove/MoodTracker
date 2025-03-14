package ru.meowlove.MoodTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.meowlove.MoodTracker.models.Account;
import ru.meowlove.MoodTracker.models.Mood;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Integer> {
    Optional<Mood> findByDate(Date date);

//    @Query(value = "SELECT * FROM your_entity WHERE column_name = :value AND another_column < :maxValue", nativeQuery = true)
//    List<YourEntity> findByColumnNameAndAnotherColumnLessThan(
//            @Param("value") String value,
//            @Param("maxValue") double maxValue
//    );
}
