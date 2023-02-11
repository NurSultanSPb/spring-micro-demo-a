package kz.nurs.micro.demo.authservice.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private Long accountId;
    private String email;
    private String accessToken;

    public AuthResponseDto(Long accountId, String email) {
        this.accountId = accountId;
        this.email = email;
    }
}
