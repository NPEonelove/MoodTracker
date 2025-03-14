package ru.meowlove.MoodTracker.exceptions.account;

public class AccountNotCreatedException extends RuntimeException {
  public AccountNotCreatedException(String message) {
    super(message);
  }
}
