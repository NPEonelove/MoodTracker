//package ru.meowlove.MoodTracker.utils;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.meowlove.MoodTracker.models.Account;
//
//@Component
//public class AccountConverter {
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public AccountConverter(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    public Account convertToAccount(AccountDTO accountDTO) {
//        return modelMapper.map(accountDTO, Account.class);
//    }
//
//    public AccountDTO convertToAccountDTO(Account account) {
//        return modelMapper.map(account, AccountDTO.class);
//    }
//}
