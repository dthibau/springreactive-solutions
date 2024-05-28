package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("secret")).roles("ADMIN")
				.build();
		UserDetails bob = User.withUsername("bob").password(passwordEncoder().encode("secret")).roles("USER").build();

		return new MapReactiveUserDetailsService(admin,bob);
	}

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.csrf(csrfSpec -> csrfSpec.disable()).authorizeExchange( acl -> acl
				.pathMatchers(HttpMethod.POST, "/accounts").hasRole("ADMIN")
				.pathMatchers("/accounts").authenticated()
				.anyExchange().permitAll())
				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
