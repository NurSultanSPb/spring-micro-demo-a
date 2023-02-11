package kz.nurs.micro.demo.authservice.model.dto.req;

import java.time.LocalDate;

public record SignupRequestDto(String email,
                               String password,
                               String roleName,
                               String firstName,
                               String lastName,
                               LocalDate birthdayDate) {
}
