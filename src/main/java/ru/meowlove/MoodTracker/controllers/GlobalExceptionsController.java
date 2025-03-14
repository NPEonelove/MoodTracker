package ru.meowlove.MoodTracker.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.meowlove.MoodTracker.exceptions.account.*;

import java.net.http.HttpResponse;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsController {

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountNotCreatedException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountNotCreatedException(AccountNotCreatedException ex) {
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountNotCreatedException(AccountNotFoundException ex) {
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountIncorrectPasswordException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountIncorrectPasswordException(AccountIncorrectPasswordException ex) {
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.CONFLICT);
    }
}
