package edu.Binar.challenge.CinemaTicketReservation;

import edu.Binar.challenge.CinemaTicketReservation.security.Role;
import edu.Binar.challenge.CinemaTicketReservation.security.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.Binar.challenge.CinemaTicketReservation"})
public class CinemaTicketReservationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) {

		Role user = new Role();
		Role admin = new Role();

		user.setName(Role.ERole.ROLE_USER);
		roleRepository.save(user);

		admin.setName(Role.ERole.ROLE_ADMIN);
		roleRepository.save(admin);
	}
}
