package by.krutikov.service;

import by.krutikov.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile findById(Long id);

    Optional<Profile> findOne(Long id);

    List<Profile> findAll();

    List<Profile> findAll(int offset, int limit);

    Profile create(Profile object);

    Profile update(Profile object);

    Long delete(Long id);
}
