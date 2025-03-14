package ru.meowlove.MoodTracker.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meowlove.MoodTracker.dto.account.LoginDTO;
import ru.meowlove.MoodTracker.dto.account.RegistrationDTO;
import ru.meowlove.MoodTracker.exceptions.account.AccountAlreadyExistsException;
import ru.meowlove.MoodTracker.exceptions.account.AccountIncorrectPasswordException;
import ru.meowlove.MoodTracker.exceptions.account.AccountNotFoundException;
import ru.meowlove.MoodTracker.models.Account;
import ru.meowlove.MoodTracker.repositories.AccountRepository;
//import ru.meowlove.MoodTracker.utils.AccountConverter;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
//    private final AccountConverter accountConverter;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public void registerAccount(RegistrationDTO registrationDTO) {
        if (accountRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists");
        }
        accountRepository.save(modelMapper.map(registrationDTO, Account.class));
    }

    public Account loginAccount(LoginDTO loginDTO) {
        Account account = accountRepository.findByUsername(loginDTO.getUsername()).orElseThrow(()
                -> new AccountNotFoundException("Account not found"));
        if (!account.getPassword().equals(loginDTO.getPassword())) {
            throw new AccountIncorrectPasswordException("Incorrect password");
        } else {
            return account;
        }
    }
}
