package ru.meowlove.MoodTracker.dto.account;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginDTO {

    private String username;

    private String password;

}
