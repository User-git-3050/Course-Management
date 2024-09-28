package az.mscoursegateway.filter;

import az.mscoursegateway.exception.UnauthorizedException;
import az.mscoursegateway.util.PathMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private final PathMatcher pathMatcher;

    public RouteValidator(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }


    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register/student",
            "/api/v1/auth/register/instructor"
    );

    private static final List<String> adminApiEndpoints = List.of("**");
    private static final List<String> instructorApiEndpoints = List.of("/api/reports/**", "/api/courses/**");
    private static final List<String> studentApiEndpoints = List.of("/api/enrollment");

//    private final Map<Predicate<ServerHttpRequest>, String> routeRoleMap = Map.of(
//            request -> adminApiEndpoints.contains(request.getURI().getPath()), "ADMIN",
//            request -> instructorApiEndpoints.contains(request.getURI().getPath()), "INSTRUCTOR",
//            request -> studentApiEndpoints.contains(request.getURI().getPath()), "STUDENT"
//    );
//
//    public void validateRoute(ServerWebExchange exchange, String role) {
//        ServerHttpRequest request = exchange.getRequest();
//
//         routeRoleMap.entrySet().stream()
//                .filter(entry -> entry.getKey().test(request))
//                .filter(entry -> !entry.getValue().equals(role))
//                .findFirst()
//                .map(entry ->{throw new UnauthorizedException("Unauthorized");
//                });
//
//    }

    private final Map<String,List<String>> routeRole = Map.of(
            "ADMIN" , List.of("**"),
            "INSTRUCTOR" , List.of("/api/v1/reports/**", "/api/v1/courses/**"),
            "STUDENT" , List.of("/api/v1/enrollments/**")
    );

    public void validateRoute(ServerWebExchange exchange, String role) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> endpoints = routeRole.get(role);
        List<Boolean> isMatched = new ArrayList<>();
        for (String endpoint : endpoints) {
            isMatched.add(pathMatcher.match(request.getURI().getPath(),endpoint));
        }
        if(!isMatched.contains(true)) {
            throw new UnauthorizedException("Unauthorized");
        }




    }

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints.
                    stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
