package ru.meowlove.MoodTracker.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.meowlove.MoodTracker.dto.account.JwtAuthenticationResponseDTO;
import ru.meowlove.MoodTracker.dto.account.LoginDTO;
import ru.meowlove.MoodTracker.dto.account.RegistrationDTO;
import ru.meowlove.MoodTracker.exceptions.account.AccountNotCreatedException;
import ru.meowlove.MoodTracker.models.Account;
import ru.meowlove.MoodTracker.services.AccountService;
import ru.meowlove.MoodTracker.services.AuthenticationService;

@CrossOrigin(value = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

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

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponseDTO> signIn(@RequestBody @Valid LoginDTO request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
//    private final AccountService accountService;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public AuthenticationController(AccountService accountService, ModelMapper modelMapper) {
//        this.accountService = accountService;
//        this.modelMapper = modelMapper;
//    }
//
//    @PostMapping("/registration")
//    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult bindingResult,) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                errorMsg.append(error.getDefaultMessage()).append(". ");
//            }
//            throw new AccountNotCreatedException(errorMsg.toString());
//        }
//        accountService.save(modelMapper.map(registrationDTO, Account.class));
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
//        Account account = accountService.loginAccount(loginDTO);
//        session.setAttribute("accountUsername", account.getUsername());
//        return ResponseEntity.ok(HttpStatus.OK);
//    }


}
