package ru.meowlove.MoodTracker.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {

    @NotBlank(message = "Логин не должен быть пустым")
    @Size(min = 4, max = 32, message = "Длина логина может быть от 4 до 32 символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 4, max = 32, message = "Длина пароля может быть от 4 до 32 символов")
    private String password;

}
