package by.krutikov.service;

import by.krutikov.entity.Media;

import java.util.List;
import java.util.Optional;

public interface MediaService {
    Media findById(Long id);

    Optional<Media> findOne(Long id);

    List<Media> findAll();

    List<Media> findAll(int offset, int limit);

    Media create(Media object);

    Media update(Media object);

    Long delete(Long id);
}
