package edu.mum.rentalHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})

public class RentalHouseApplication {

	
	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	public static void main(String[] args) {
		SpringApplication.run(RentalHouseApplication.class, args);
	}


}
