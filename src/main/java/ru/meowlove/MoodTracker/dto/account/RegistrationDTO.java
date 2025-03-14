package ru.meowlove.MoodTracker.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationDTO {

    @NotBlank(message = "Логин не должен быть пустым")
    @Size(min = 4, max = 32, message = "Длина логина может быть от 4 до 32 символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 4, max = 32, message = "Длина пароля может быть от 4 до 32 символов")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
