package kz.nurs.micro.demo.authservice.model.dto.req;

import java.time.LocalDate;

public record ProfileCreateRequestDto(String firstName,
                                      String lastName,
                                      LocalDate birthdayDate,
                                      Long accountId) {
}
