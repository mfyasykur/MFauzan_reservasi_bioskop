package edu.Binar.challenge.CinemaTicketReservation.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {@Server(url = "/", description = "Default Server URL")}
)
public class SwaggerOpenAPIConfig {

    @Bean
    public OpenAPI cinemaOpenAPI(){

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description(
                                        "Provide the JWT token, JWT token can be obtained from the Login API."
                                )
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("MyCinema REST API")
                        .description("REST API aplikasi MyCinema")
                        .version("/api/mycinema-v1")
                );
    }
}
