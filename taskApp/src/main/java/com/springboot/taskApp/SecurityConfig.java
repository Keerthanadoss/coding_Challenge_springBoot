package com.springboot.taskApp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.taskApp.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService; 
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/auth/token").permitAll()
                       .requestMatchers("/auth/signup").permitAll()
                       .requestMatchers("/task/getAll").permitAll()
                       .requestMatchers("/task/getById/{taskId}").hasAnyRole("USER", "ADMIN")
                       .requestMatchers("/task/add").permitAll()
                       .requestMatchers("/task/status").permitAll()
                       .requestMatchers("/task/priority").permitAll()
                       .requestMatchers("/task/update/{taskId}").hasRole("ADMIN")
                       .requestMatchers("/task/delete/{taskId}").permitAll()
                       .requestMatchers("/admin/hello").hasRole("ADMIN")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       .anyRequest().authenticated()
               )
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );

       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService);
       authProvider.setPasswordEncoder(passwordEncoder);
       return authProvider;
   }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }

	
	@Bean
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder;
	}

}
