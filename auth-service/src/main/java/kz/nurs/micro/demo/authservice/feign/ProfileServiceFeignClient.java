package kz.nurs.micro.demo.authservice.feign;

import kz.nurs.micro.demo.authservice.model.dto.req.ProfileCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("profile-service")
public interface ProfileServiceFeignClient {
    @PostMapping("/api/inner/profile")
    ResponseEntity saveProfile(ProfileCreateRequestDto profileCreateRequestDto);
}
