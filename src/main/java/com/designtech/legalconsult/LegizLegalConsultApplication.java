package com.designtech.legalconsult;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class LegizLegalConsultApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegizLegalConsultApplication.class, args);
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
     @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
}
