package ru.meowlove.MoodTracker.exceptions.account;

public class AccountIncorrectPasswordException extends RuntimeException {
  public AccountIncorrectPasswordException(String message) {
    super(message);
  }
}
