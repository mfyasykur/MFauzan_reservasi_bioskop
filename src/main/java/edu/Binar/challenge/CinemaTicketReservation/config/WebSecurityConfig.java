package edu.Binar.challenge.CinemaTicketReservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build());

        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build());

        return userDetailsManager;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("user").password("user123").roles("USER")
//                .and()
//                .withUser("admin").password("admin123").roles("USER", "ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/mycinema-v1/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

//        httpSecurity.cors();
    }
}
