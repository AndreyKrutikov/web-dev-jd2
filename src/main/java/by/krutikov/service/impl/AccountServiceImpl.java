package by.krutikov.service.impl;

import by.krutikov.entity.Account;
import by.krutikov.entity.Role;
import by.krutikov.repository.account.AccountRepository;
import by.krutikov.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> findOne(Long id) {
        return Optional.of(accountRepository.findById(id));  //ofNullable is better???
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAll(int offset, int limit) {
        return accountRepository.findAll(offset, limit);
    }

    @Override
    public Account create(Account object) {
        object.setDateCreated(new Timestamp(new Date().getTime()));
        object.setDateModified(new Timestamp(new Date().getTime()));
        object.setRole(Role.USER);//check !Role.ADMIN
        object.setIsLocked(Boolean.FALSE);
        return accountRepository.create(object);
    }

    @Override
    public Account update(Account object) {
        object.setDateModified(new Timestamp(new Date().getTime()));
        return accountRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return accountRepository.delete(id);
    }
}
