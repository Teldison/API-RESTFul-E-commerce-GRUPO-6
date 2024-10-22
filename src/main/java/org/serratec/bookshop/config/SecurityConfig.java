package org.serratec.bookshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	//	http.authorizeHttpRequests(r -> r.anyRequest().authenticated())
	//	.httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(authorize -> authorize
		.requestMatchers(HttpMethod.GET, "/pedidos").permitAll()
		.requestMatchers(HttpMethod.GET, "/pedidos/{id}").permitAll()
		.requestMatchers(HttpMethod.POST, "/pedidos").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/pedidos/{id}").hasAnyRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/pedidos/{id}").hasRole("ADM")
		
		.requestMatchers(HttpMethod.GET, "/livros").permitAll()
		.requestMatchers(HttpMethod.GET, "/livros/{id}").permitAll()
		.requestMatchers(HttpMethod.GET, "/livros/nome/{nome}").permitAll()
		.requestMatchers(HttpMethod.POST, "/livros").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/livros/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/livros/{id}").hasRole("ADM")
		
		.requestMatchers(HttpMethod.GET, "/clientes").permitAll()
		.requestMatchers(HttpMethod.GET, "/clientes/{id}").permitAll()
		.requestMatchers(HttpMethod.GET, "/clientes/pedido/{id}").permitAll()
		.requestMatchers(HttpMethod.POST, "/clientes").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/clientes/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/clientes/{id}").hasRole("ADM")
		.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
		.anyRequest().authenticated())
		.csrf(csrf -> csrf.disable())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager usuariosDetalhes() {
		UserDetails usuario = User.builder()
				.username("admin")
				.password(encoder().encode("1234"))
				.roles("ADM").build();
		
		UserDetails usuario2 = User.builder()
				.username("rh")
				.password(encoder().encode("12345"))
				.roles("RH").build();
		
		return new InMemoryUserDetailsManager(usuario, usuario2);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
