package org.serratec.bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);
	}
	
	@Configuration
	public class AppConfig {

	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

}
	
}
