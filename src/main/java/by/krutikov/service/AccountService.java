package by.krutikov.service;

import by.krutikov.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account findById(Long id);

    Optional<Account> findOne(Long id);

    List<Account> findAll();

    List<Account> findAll(int offset, int limit);

    Account create(Account object);

    Account update(Account object);

    Long delete(Long id);
}
