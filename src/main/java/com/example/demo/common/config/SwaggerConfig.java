package com.example.demo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    private static final String SECURITY_SCHEME_NAME = "authorization";	// 추가

    @Value("${swagger.server-url}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi jwtApi() {
        return GroupedOpenApi.builder()
                .group("jwt-api")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI OpenApi() {

        return new OpenAPI()
                .addServersItem(new Server().url(serverUrl))
                .components(new Components()
                        // 여기부터 추가 부분
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                // 여기까지
                .info(new Info()
                        .title("demo 서비스 API 명세서")
                        .description("스프링부트 demo 서비스 CRUD 실습 API 명세서")
                        .version("V1"));
    }
}
