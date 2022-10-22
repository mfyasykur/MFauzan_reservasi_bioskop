package edu.Binar.challenge.CinemaTicketReservation.security;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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

//    @Bean
//    public CorsFilter corsFilter() {
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of("*"));
//        corsConfiguration.setMaxAge(Duration.ofMinutes(10));
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return new CorsFilter();
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/mycinema-v1/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/mycinema-v1/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic();
//                .logout().disable();

//        httpSecurity.cors();

    }
}
