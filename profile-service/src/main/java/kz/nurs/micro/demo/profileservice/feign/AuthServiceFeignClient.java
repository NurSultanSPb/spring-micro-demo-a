package kz.nurs.micro.demo.profileservice.feign;

import kz.nurs.micro.demo.profileservice.model.dto.resp.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("auth-service")
public interface AuthServiceFeignClient {

    @GetMapping("/api/internal/account/{id}")
    ResponseEntity<AccountResponseDto> getAdditinalInfoFromAuthServiceForProfileService(@PathVariable Long id);
}
