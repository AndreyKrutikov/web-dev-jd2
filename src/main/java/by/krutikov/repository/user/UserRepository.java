package by.krutikov.repository.user;

import by.krutikov.entity.User;
import by.krutikov.repository.CRUDRepository;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {
    List<User> findByFullName(String name, String surname);
}
