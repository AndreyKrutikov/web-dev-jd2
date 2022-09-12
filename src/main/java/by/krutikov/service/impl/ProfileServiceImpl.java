package by.krutikov.service.impl;

import by.krutikov.entity.Profile;
import by.krutikov.repository.profile.ProfileRepository;
import by.krutikov.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public Optional<Profile> findOne(Long id) {
        return profileRepository.findOne(id);
    }

    @Override
    public List<Profile> findAll() {
         return profileRepository.findAll() ;
    }

    @Override
    public List<Profile> findAll(int offset, int limit) {
        return profileRepository.findAll(offset, limit);
    }

    @Override
    public Profile create(Profile object) {
        object.setDateCreated(new Timestamp(new Date().getTime()));
        object.setDateModified(new Timestamp(new Date().getTime()));
        object.setIsVisible(Boolean.TRUE);
        return profileRepository.create(object);
    }

    @Override
    public Profile update(Profile object) {
        object.setDateModified(new Timestamp(new Date().getTime()));
        return profileRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return profileRepository.delete(id);
    }
}
