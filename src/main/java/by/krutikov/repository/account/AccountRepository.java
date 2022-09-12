package by.krutikov.repository.account;

import by.krutikov.entity.Account;
import by.krutikov.repository.CRUDRepository;

public interface AccountRepository extends CRUDRepository<Long, Account> {
    //List<Account> findByFullName(String name, String surname);
}
