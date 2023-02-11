package kz.nurs.micro.demo.authservice.service;

import kz.nurs.micro.demo.authservice.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long accountId);
    User save(User user);
    User update(User user);
    void deleteById(Long accountId);
    Optional<User> findByEmail(String email);
}
