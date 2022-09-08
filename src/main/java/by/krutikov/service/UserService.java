package by.krutikov.service;

import by.krutikov.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findById(Long id);

    Optional<User> findOne(Long id);

    List<User> findAll();

    List<User> findAll(int offset, int limit);

    List<User> findByFullName(String name, String surname);

    User create(User object);

    User update(User object);

    Long delete(Long id);
}
