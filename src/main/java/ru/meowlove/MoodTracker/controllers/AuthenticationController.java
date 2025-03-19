package ru.meowlove.MoodTracker.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.account.JwtAuthenticationResponseDTO;
import ru.meowlove.MoodTracker.dto.account.LoginDTO;
import ru.meowlove.MoodTracker.dto.account.RegistrationDTO;
import ru.meowlove.MoodTracker.exceptions.account.AccountNotCreatedException;
import ru.meowlove.MoodTracker.services.AuthenticationService;

@CrossOrigin(value = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "AuthenticationController", description = "Контроллер для аутентификации. Возвращает jwt токен")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Регистрация пользователя"
    )
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponseDTO> signUp(@RequestBody @Valid RegistrationDTO request,
                                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMsg.append(error.getDefaultMessage()).append(". ");
            }
            throw new AccountNotCreatedException(errorMsg.toString());
        }
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @Operation(
            summary = "Авторизация пользователя"
    )
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponseDTO> signIn(@RequestBody @Valid LoginDTO request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
