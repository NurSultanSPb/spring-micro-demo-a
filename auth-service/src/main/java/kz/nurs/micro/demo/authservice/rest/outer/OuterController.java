package kz.nurs.micro.demo.authservice.rest.outer;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.nurs.micro.demo.authservice.feign.ProfileServiceFeignClient;
import kz.nurs.micro.demo.authservice.model.converter.MapperFromEntity;
import kz.nurs.micro.demo.authservice.model.converter.MapperToEntity;
import kz.nurs.micro.demo.authservice.model.dto.req.LoginRequestDto;
import kz.nurs.micro.demo.authservice.model.dto.req.ProfileCreateRequestDto;
import kz.nurs.micro.demo.authservice.model.dto.req.SignupRequestDto;
import kz.nurs.micro.demo.authservice.model.dto.resp.AuthResponseDto;
import kz.nurs.micro.demo.authservice.model.entity.User;
import kz.nurs.micro.demo.authservice.service.UserService;
import kz.nurs.micro.demo.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OuterController {

    private final MapperToEntity mapperToEntity;
    private final UserService userService;
    private final MapperFromEntity mapperFromEntity;
    private final ProfileServiceFeignClient profileServiceFeignClient;
    private final JwtUtil jwtUtil;

    //должно сохранять/ регистрировать пользователя и в сервисе авторизации и в сервисе профиля
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignupRequestDto signupRequestDto) {
        User user = mapperToEntity.createUserFromSignupReqDto(signupRequestDto);
        userService.save(user);
        profileServiceFeignClient.saveProfile(new ProfileCreateRequestDto(
                signupRequestDto.firstName(),
                signupRequestDto.lastName(),
                signupRequestDto.birthdayDate(),
                user.getAccountId()));
        return new ResponseEntity(HttpStatus.OK);
    }

    // здесь должен работать механизм генерации токена JWT
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = userService.findByEmail(loginRequestDto.email());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Not found user with email: " + loginRequestDto.email());
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(loginRequestDto.password())) {
            throw new RuntimeException("Incorrect password");
        }
        AuthResponseDto authRespDtoFromEntity = mapperFromEntity.createAuthRespDtoFromEntity(user);

        String token = jwtUtil.generateToken(user);
        authRespDtoFromEntity.setAccessToken(token);

        return ResponseEntity.ok(authRespDtoFromEntity);
    }
}
