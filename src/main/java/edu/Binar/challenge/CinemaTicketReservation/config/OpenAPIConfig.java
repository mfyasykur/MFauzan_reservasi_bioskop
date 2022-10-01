package edu.Binar.challenge.CinemaTicketReservation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI cinemaOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("MyCinema Open API")
                        .description("Open API aplikasi MyCinema")
                );
    }
}
