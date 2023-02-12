package kz.nurs.micro.demo.authservice.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import kz.nurs.micro.demo.authservice.model.entity.User;
import kz.nurs.micro.demo.authservice.service.UserService;
import kz.nurs.micro.demo.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
@Component
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Value("${jwt.header}")
    private String header;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtTokenFromHeader = ((HttpServletRequest) request).getHeader(header);
        String jwtToken = null;
        String email = null;
        if (jwtTokenFromHeader != null && jwtTokenFromHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenFromHeader.substring(7);
            email = jwtUtil.getUsernameFromToken(jwtToken);
        }
        if (email != null) {
            Optional<User> optionalUser = userService.findByEmail(email);
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("user is empty");
            }
            User user = optionalUser.get();
            if(jwtUtil.validateToken(jwtToken, user)) {
                chain.doFilter(request, response);
            } else {
                throw new RuntimeException("invalid token");
            }
        }


    }
}
