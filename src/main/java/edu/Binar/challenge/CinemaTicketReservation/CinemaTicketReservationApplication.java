package edu.Binar.challenge.CinemaTicketReservation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.Binar.challenge.CinemaTicketReservation"})
public class CinemaTicketReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
