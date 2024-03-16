package top.haidong556.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.haidong556.filter.AuthenticationFilter;
import top.haidong556.filter.CorsFilter;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter, CorsFilter corsFilter){
        return builder.routes()
                .route("deal-server",p->p
                        .method("GET").and()
                        .path("/deal/**")
                        .filters(f -> f.filter(authenticationFilter).filter(corsFilter))
                        .uri("http://localhost:8080")
                )
                .route("chat-server",predicateSpec -> {
                    return predicateSpec.method("GET")
                            .and().path("/chat/**")
                            .filters(f -> f.filter(authenticationFilter).filter(corsFilter))
                            .uri("http://localhost:8888");
                })
                .route("oauth-server",predicateSpec -> {
                    return predicateSpec.method("POST")
                            .and().path("/oauth/**")
                            .filters(f -> f.filter(authenticationFilter).filter(corsFilter))
                            .uri("http://localhost:9000")
                            ;
                })
                .build();
    }

}
