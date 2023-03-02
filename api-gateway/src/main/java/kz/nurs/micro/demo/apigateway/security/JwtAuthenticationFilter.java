package kz.nurs.micro.demo.apigateway.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import kz.nurs.micro.demo.apigateway.util.JwtUtil;
import kz.nurs.micro.demo.apigateway.util.RouterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;
    private final RouterValidator routerValidator;

    private static final Set<String> PUBLIC_PATHS = Set.of("/api/auth/signup", "/api/auth/login");
    @Value("${jwt.header}")
    private String header;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        System.out.println("Here we are");
        if (isPublicPath(uri)) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("Here we are after if clause");


        String jwtTokenFromHeader = ((HttpServletRequest) request).getHeader(header);
        System.out.println("Got jwt from header " + jwtTokenFromHeader);
        String jwtToken = null;
        String email = null;
        if (jwtTokenFromHeader != null && jwtTokenFromHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenFromHeader.substring(7);
            System.out.println("Got jwt without Bear.... " + jwtToken);
            String[] parts = jwtToken.split("\\.");
            String decodedHeader = JwtUtil.decode(parts[0]);
            String decodedPayload = JwtUtil.decode(parts[1]);
            String decodedSecret = JwtUtil.decode(parts[2]);
            System.out.println(decodedHeader + "." + decodedPayload + "." + decodedSecret);
            chain.doFilter(request, response);
        }
    }

    private Boolean isPublicPath(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(a -> uri.startsWith(a));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }
            final String token = this.getAuthHeader(request).substring(7);
            if (jwtUtil.isInvalid(token)) {
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            }
            if (routerValidator.isForbidden.test(request)) {
                if (!jwtUtil.getRoleFromToken(token).equalsIgnoreCase("USER")) {
                    return this.onError(exchange, "No authorities to access", HttpStatus.FORBIDDEN);
                }
            }
        }
        return chain.filter(exchange);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }
}
