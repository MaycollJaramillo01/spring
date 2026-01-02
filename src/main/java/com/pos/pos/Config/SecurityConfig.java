package com.pos.pos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pos.pos.Filter.ClerkJwtFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final ClerkJwtFilter clerkJwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configure(http))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/public/**").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**",
								"/swagger-resources/**", "/swagger-ui.html",
								"/webjars/**") // swagger endpoints
						.permitAll()
						.anyRequest().authenticated() // all the other requests are
										// authenticated
				)
				.addFilterBefore(clerkJwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
