package by.krutikov.repository.account;

import by.krutikov.entity.Account;
import by.krutikov.entity.User;
import by.krutikov.repository.CRUDRepository;

import java.util.List;

public interface AccountRepository extends CRUDRepository<Long, Account> {
    //List<Account> findByFullName(String name, String surname);
}
