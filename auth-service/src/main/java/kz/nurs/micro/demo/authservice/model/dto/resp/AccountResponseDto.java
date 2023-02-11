package kz.nurs.micro.demo.authservice.model.dto.resp;

import kz.nurs.micro.demo.authservice.model.enums.RoleNameEnum;

public record AccountResponseDto(long id,
                                 String email,
                                 RoleNameEnum role,
                                 boolean enable
) {
}
