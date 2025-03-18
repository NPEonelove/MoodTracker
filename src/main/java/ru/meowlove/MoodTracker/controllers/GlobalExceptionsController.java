package ru.meowlove.MoodTracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.meowlove.MoodTracker.exceptions.account.*;
import ru.meowlove.MoodTracker.exceptions.mood.MoodErrorResponse;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotAddedException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotHavePermissionsForEditException;
import ru.meowlove.MoodTracker.exceptions.mood.MoodNotHavePermissionsForGiveException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsController {

    // account errors
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
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountIncorrectPasswordException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountIncorrectPasswordException(AccountIncorrectPasswordException ex) {
        return new ResponseEntity<>(new AccountErrorResponse(ex.getMessage(), new Date()), HttpStatus.UNAUTHORIZED);
    }

    // mood errors
    @ExceptionHandler(MoodNotAddedException.class)
    public ResponseEntity<MoodErrorResponse> handleMoodNotAddedException(MoodNotAddedException ex) {
        return new ResponseEntity<>(new MoodErrorResponse(ex.getMessage(), new Date()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MoodNotHavePermissionsForEditException.class)
    public ResponseEntity<MoodErrorResponse> handleMoodNotHavePermissionsForEditException(MoodNotHavePermissionsForEditException ex) {
        return new ResponseEntity<>(new MoodErrorResponse(ex.getMessage(), new Date()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MoodNotHavePermissionsForGiveException.class)
    public ResponseEntity<MoodErrorResponse> handleMoodNotHavePermissionsForGiveException(MoodNotHavePermissionsForGiveException ex) {
        return new ResponseEntity<>(new MoodErrorResponse(ex.getMessage(), new Date()), HttpStatus.FORBIDDEN);
    }
}
