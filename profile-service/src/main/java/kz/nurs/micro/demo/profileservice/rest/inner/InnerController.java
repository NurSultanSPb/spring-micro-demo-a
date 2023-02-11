package kz.nurs.micro.demo.profileservice.rest.inner;

import kz.nurs.micro.demo.profileservice.model.converter.MapperToEntity;
import kz.nurs.micro.demo.profileservice.model.dto.req.ProfileCreateRequestDto;
import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import kz.nurs.micro.demo.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inner")
@RequiredArgsConstructor
public class InnerController {

    private final ProfileService profileService;
    private final MapperToEntity mapperToEntity;

    @PostMapping("/profile")
    public ResponseEntity saveProfile(@RequestBody ProfileCreateRequestDto profileCreateRequestDto) {
        Profile profile = mapperToEntity.createProfileFromCreateReqDto(profileCreateRequestDto);
        profileService.save(profile);
        return new ResponseEntity(HttpStatus.OK);
    }
}
