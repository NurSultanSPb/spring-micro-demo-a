package kz.nurs.micro.demo.authservice.model.converter;

import kz.nurs.micro.demo.authservice.model.dto.req.SignupRequestDto;
import kz.nurs.micro.demo.authservice.model.entity.User;
import kz.nurs.micro.demo.authservice.model.enums.RoleNameEnum;
import org.springframework.stereotype.Component;

@Component
public class MapperToEntity {

    public User createUserFromSignupReqDto(SignupRequestDto signupRequestDto) {
        User user = new User();
        user.setEmail(signupRequestDto.email());
        user.setPassword(signupRequestDto.password());
        user.setRole(RoleNameEnum.valueOf(signupRequestDto.roleName()));
        return user;
    }

}
