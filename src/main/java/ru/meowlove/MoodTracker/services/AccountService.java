package ru.meowlove.MoodTracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meowlove.MoodTracker.exceptions.account.AccountAlreadyExistsException;
import ru.meowlove.MoodTracker.exceptions.account.AccountNotFoundException;
import ru.meowlove.MoodTracker.models.Account;
import ru.meowlove.MoodTracker.repositories.AccountRepository;
//import ru.meowlove.MoodTracker.utils.AccountConverter;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public Account save(Account user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public Account create(Account user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new AccountAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public Account getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public Account getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /*
      Выдача прав администратора текущему пользователю
      <p>
      Нужен для демонстрации

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
     */
}
