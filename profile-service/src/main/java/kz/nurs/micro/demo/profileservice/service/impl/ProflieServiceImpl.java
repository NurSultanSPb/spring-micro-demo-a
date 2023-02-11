package kz.nurs.micro.demo.profileservice.service.impl;

import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import kz.nurs.micro.demo.profileservice.repository.ProfileRepository;
import kz.nurs.micro.demo.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProflieServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Optional<Profile> findById(Long profileId) {
        return profileRepository.findById(profileId);
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteById(Long profileId) {
        profileRepository.deleteById(profileId);
    }
}
