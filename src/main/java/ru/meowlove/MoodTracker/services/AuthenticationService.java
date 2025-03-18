package ru.meowlove.MoodTracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.meowlove.MoodTracker.dto.account.JwtAuthenticationResponseDTO;
import ru.meowlove.MoodTracker.dto.account.LoginDTO;
import ru.meowlove.MoodTracker.dto.account.RegistrationDTO;
import ru.meowlove.MoodTracker.models.Account;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountService accountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponseDTO signUp(RegistrationDTO request) {

        var user = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        accountService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDTO(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponseDTO signIn(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = accountService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDTO(jwt);
    }
}
