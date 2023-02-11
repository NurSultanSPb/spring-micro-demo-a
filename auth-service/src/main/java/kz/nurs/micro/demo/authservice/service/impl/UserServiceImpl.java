package kz.nurs.micro.demo.authservice.service.impl;

import kz.nurs.micro.demo.authservice.model.entity.User;
import kz.nurs.micro.demo.authservice.model.enums.RoleNameEnum;
import kz.nurs.micro.demo.authservice.repository.UserRepository;
import kz.nurs.micro.demo.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> findById(Long accountId) {
        return userRepository.findById(accountId);
    }

    @Override
    public User save(User user) {
        user.setEnable(user.getRole().equals(RoleNameEnum.USER));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long accountId) {
        userRepository.deleteById(accountId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
