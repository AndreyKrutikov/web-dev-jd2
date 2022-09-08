package by.krutikov.service.impl;

import by.krutikov.entity.User;
import by.krutikov.repository.user.UserRepository;
import by.krutikov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(int offset, int limit) {
        return userRepository.findAll(offset, limit);
    }

    @Override
    public List<User> findByFullName(String name, String surname) {
        return userRepository.findByFullName(name,surname);
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return userRepository.delete(id);
    }
}
