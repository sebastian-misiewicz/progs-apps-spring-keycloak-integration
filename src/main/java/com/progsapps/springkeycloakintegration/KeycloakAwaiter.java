package com.progsapps.springkeycloakintegration;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.progsapps.springkeycloakintegration.controller.ProductController;

@Component
public class KeycloakAwaiter {

    public static Logger LOGGER = Logger.getLogger(KeycloakAwaiter.class.getName());

    private final String issuerUri;

    private final RestTemplate restTemplate;

    public KeycloakAwaiter(@Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") String issuerUri, RestTemplate restTemplate) {
        this.issuerUri = issuerUri;
        this.restTemplate = restTemplate;
        try {
            init();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() throws InterruptedException {
        boolean isKeycloakNotAlive = true;
        int tick = 0;
        while (isKeycloakNotAlive) {
            LOGGER.info(String.format("Waiting for Keycloak to be alive. Tick: %d.", ++tick));
            isKeycloakNotAlive = !isKeycloakAlive();
            Thread.sleep(1000);
        }
    }

    private boolean isKeycloakAlive() {
        try {
            LOGGER.info(restTemplate.getForObject(issuerUri, String.class));
            return true;
        } catch (RestClientException exception) {
            LOGGER.info(exception.getMessage());
            return false;
        }
    }


}
