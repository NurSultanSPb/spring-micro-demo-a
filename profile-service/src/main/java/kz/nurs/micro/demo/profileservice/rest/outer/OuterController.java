package kz.nurs.micro.demo.profileservice.rest.outer;

import kz.nurs.micro.demo.profileservice.feign.AuthServiceFeignClient;
import kz.nurs.micro.demo.profileservice.model.converter.MapperFromEntity;
import kz.nurs.micro.demo.profileservice.model.converter.MapperToEntity;
import kz.nurs.micro.demo.profileservice.model.dto.req.ProfileUpdateRequestDto;
import kz.nurs.micro.demo.profileservice.model.dto.resp.AccountResponseDto;
import kz.nurs.micro.demo.profileservice.model.dto.resp.ProfileResponseDto;
import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import kz.nurs.micro.demo.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OuterController {

    private final ProfileService profileService;
    private final AuthServiceFeignClient authServiceFeignClient;
    private final MapperFromEntity mapperFromEntity;
    private final MapperToEntity mapperToEntity;

    @PutMapping("/profile")
    public ResponseEntity<Profile> updateProfile(@RequestHeader(name = "accountId") Long accountId, @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto) {
        Profile profile = mapperToEntity.createProfileFromUpdateReqDto(profileUpdateRequestDto);
        profile.setProfileId(accountId);
        Profile updatedProfile = profileService.update(profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDto> getProfile(@RequestParam Long accountId) {
        Optional<Profile> optionalProfile = profileService.findById(accountId);
        if(optionalProfile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Profile profile = optionalProfile.get();
        ResponseEntity<AccountResponseDto> accountResponseDto = authServiceFeignClient.getAdditinalInfoFromAuthServiceForProfileService(accountId);
        ProfileResponseDto responseDtoFromProfileAndAccountResp = mapperFromEntity.getResponseDtoFromProfileAndAccountResp(
                profile, accountResponseDto.getBody());
        return ResponseEntity.ok(responseDtoFromProfileAndAccountResp);
    }
}
