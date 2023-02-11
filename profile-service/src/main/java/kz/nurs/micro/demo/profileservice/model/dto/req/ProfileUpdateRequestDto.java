package kz.nurs.micro.demo.profileservice.model.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProfileUpdateRequestDto(String firstName,
                                      String lastName,
                                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
                                      LocalDate birthday
) {
}
