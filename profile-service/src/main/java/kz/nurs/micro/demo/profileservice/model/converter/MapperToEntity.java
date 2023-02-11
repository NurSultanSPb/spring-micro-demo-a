package kz.nurs.micro.demo.profileservice.model.converter;

import kz.nurs.micro.demo.profileservice.model.dto.req.ProfileCreateRequestDto;
import kz.nurs.micro.demo.profileservice.model.dto.req.ProfileUpdateRequestDto;
import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class MapperToEntity {

    public Profile createProfileFromCreateReqDto(ProfileCreateRequestDto profileCreateRequestDto) {
        Profile profile = new Profile();
        profile.setProfileId(profileCreateRequestDto.accountId());
        profile.setFirstName(profileCreateRequestDto.firstName());
        profile.setLastName(profileCreateRequestDto.lastName());
        profile.setBirthdayDate(profileCreateRequestDto.birthdayDate());
        return profile;
    }

    public Profile createProfileFromUpdateReqDto(ProfileUpdateRequestDto profileUpdateRequestDto) {
        Profile profile = new Profile();
        profile.setFirstName(profileUpdateRequestDto.firstName());
        profile.setLastName(profileUpdateRequestDto.lastName());
        profile.setBirthdayDate(profileUpdateRequestDto.birthday());
        return profile;
    }
}
