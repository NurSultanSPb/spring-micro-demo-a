package kz.nurs.micro.demo.apigateway.util;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints= List.of(
            "/api/auth/signup",
            "/api/auth/login"
    );

    public static final List<String> closedEndPoints = List.of("/profile");

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> isForbidden =
            request -> closedEndPoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));

}
