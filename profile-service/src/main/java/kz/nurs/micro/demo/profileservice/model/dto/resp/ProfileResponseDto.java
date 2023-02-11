package kz.nurs.micro.demo.profileservice.model.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.nurs.micro.demo.profileservice.model.enums.RoleNameEnum;

import java.time.LocalDate;


public record ProfileResponseDto(
        long profileId,
        long accountId,
        String firstName,
        String lastName,
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        LocalDate birthday,
        RoleNameEnum role,
        boolean enable
) {
}
