package kz.nurs.micro.demo.profileservice.model.converter;

import kz.nurs.micro.demo.profileservice.model.dto.resp.AccountResponseDto;
import kz.nurs.micro.demo.profileservice.model.dto.resp.ProfileResponseDto;
import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class MapperFromEntity {
    public ProfileResponseDto getResponseDtoFromProfileAndAccountResp(Profile profile,
                                                                      AccountResponseDto accountResponseDto) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto(
                profile.getProfileId(),
                accountResponseDto.id(),
                profile.getFirstName(),
                profile.getLastName(),
                accountResponseDto.email(),
                profile.getBirthdayDate(),
                accountResponseDto.role(),
                accountResponseDto.enable());
        return profileResponseDto;

    }
}
