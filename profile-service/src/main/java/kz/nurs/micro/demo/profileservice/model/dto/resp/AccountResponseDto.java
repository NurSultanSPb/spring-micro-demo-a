package kz.nurs.micro.demo.profileservice.model.dto.resp;

import kz.nurs.micro.demo.profileservice.model.enums.RoleNameEnum;

public record AccountResponseDto(long id,
                                 String email,
                                 RoleNameEnum role,
                                 boolean enable) {
}
