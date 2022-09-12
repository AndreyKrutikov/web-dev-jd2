package by.krutikov.service.impl;

import by.krutikov.entity.Media;
import by.krutikov.repository.media.MediaRepository;
import by.krutikov.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public Media findById(Long id) {
        return mediaRepository.findById(id);
    }

    @Override
    public Optional<Media> findOne(Long id) {
        return mediaRepository.findOne(id);
    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> findAll(int offset, int limit) {
        return mediaRepository.findAll(offset, limit);
    }

    @Override
    public Media create(Media object) {
        return mediaRepository.create(object);
    }

    @Override
    public Media update(Media object) {
        return mediaRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return mediaRepository.delete(id);
    }
}
