package kz.nurs.micro.demo.authservice.rest.inner;

import kz.nurs.micro.demo.authservice.model.converter.MapperFromEntity;
import kz.nurs.micro.demo.authservice.model.dto.resp.AccountResponseDto;
import kz.nurs.micro.demo.authservice.model.entity.User;
import kz.nurs.micro.demo.authservice.model.enums.RoleNameEnum;
import kz.nurs.micro.demo.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InnerController {

    private final UserService userService;
    private final MapperFromEntity mapperFromEntity;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountResponseDto> getAdditinalInfoFromAuthServiceForProfileService(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        User user = optionalUser.get();
        return ResponseEntity.ok(mapperFromEntity.createAccountRespDtoFromEntity(user));
    }
}
