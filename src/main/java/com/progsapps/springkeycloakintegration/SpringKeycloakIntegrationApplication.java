package com.progsapps.springkeycloakintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringKeycloakIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKeycloakIntegrationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
