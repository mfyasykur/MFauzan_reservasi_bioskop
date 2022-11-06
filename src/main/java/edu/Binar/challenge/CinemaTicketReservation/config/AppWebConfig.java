package edu.Binar.challenge.CinemaTicketReservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String invoicePath = uploadPath();

        registry.addResourceHandler("/generated-invoices/**")
                .addResourceLocations("file:/"+invoicePath+"/");
    }

    private String uploadPath(){
        Path uploadDirPath = Paths.get("./generated-invoices");

        return uploadDirPath.toFile().getAbsolutePath();
    }
}
