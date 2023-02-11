package kz.nurs.micro.demo.authservice.model.converter;

import kz.nurs.micro.demo.authservice.model.dto.resp.AccountResponseDto;
import kz.nurs.micro.demo.authservice.model.dto.resp.AuthResponseDto;
import kz.nurs.micro.demo.authservice.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MapperFromEntity {

    public AuthResponseDto createAuthRespDtoFromEntity(User user) {
        AuthResponseDto authResponseDto = new AuthResponseDto(user.getAccountId(), user.getEmail());
        return authResponseDto;
    }

    public AccountResponseDto createAccountRespDtoFromEntity(User user) {
        AccountResponseDto accountResponseDto = new AccountResponseDto(user.getAccountId(), user.getEmail(),
                user.getRole(), user.getEnable());
        return accountResponseDto;
    }
}
