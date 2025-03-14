//package ru.meowlove.MoodTracker.dto;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import ru.meowlove.MoodTracker.models.Account;
//
//import java.util.Date;
//
//public class MoodDTO {
//
//    @NotNull(message = "Значение настроения не должно быть пустым")
//    @Min(value = 1, message = "Минимальное значение = 1")
//    @Max(value = 10, message = "Максимальное значение = 10")
//    private Integer value;
//
//    @Size(max = 65536, message = "Длина комментария не может быть больше 65536 символов")
//    private String comment;
//
////    @NotNull(message = "Аккаунт должен существовать")
//    private AccountDTO account; // связанная сущность
//
//    private Date date;
//
//    private MoodEnum moodEnum;
//
//    public Integer getValue() {
//        return value;
//    }
//
//    public void setValue(Integer value) {
//        this.value = value;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public AccountDTO getAccount() {
//        return account;
//    }
//
//    public void setAccount(AccountDTO account) {
//        this.account = account;
//    }
//
//    public MoodEnum getMoodEnum() {
//        return moodEnum;
//    }
//
//    public void setMoodEnum(MoodEnum moodEnum) {
//        this.moodEnum = moodEnum;
//    }
//}
