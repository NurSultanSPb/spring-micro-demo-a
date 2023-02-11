package kz.nurs.micro.demo.profileservice.service;

import kz.nurs.micro.demo.profileservice.model.entity.Profile;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> findById(Long profileId);
    Profile save(Profile profile);
    Profile update(Profile profile);
    void deleteById(Long profileId);
}
