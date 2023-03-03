package com.progsapps.springkeycloakintegration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progsapps.springkeycloakintegration.model.AuthorizationCode;
import com.progsapps.springkeycloakintegration.model.KeycloakConfiguration;
import com.progsapps.springkeycloakintegration.model.TokenResponse;
import com.progsapps.springkeycloakintegration.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("security")
public class SecurityController {

    public static Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    private final KeycloakConfiguration keycloakConfiguration;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SecurityController(///
                              @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") String issuerUri,///
                              @Value("${spring.security.oauth2.client.registration.keycloak.client-id}") String clientId,///
                              RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        keycloakConfiguration = new KeycloakConfiguration(issuerUri, clientId);
    }


    @PostMapping(consumes = "application/json", produces = "application/json", path = "token")
    public ResponseEntity<TokenResponse> getToken(@RequestBody AuthorizationCode authorizationCode, HttpServletRequest originalRequest) throws IOException, URISyntaxException {
        String referer = originalRequest.getHeader("referer");
        if (!StringUtils.hasText(referer)) {
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("code", authorizationCode.getCode());
        valueMap.add("grant_type", "authorization_code");
        valueMap.add("client_id", keycloakConfiguration.getClientId());
        valueMap.add("redirect_uri", getUrlWithoutParameters(referer));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(///
                valueMap,///
                httpHeaders);

        String access_token_url = keycloakConfiguration.getIssuerUri();
        access_token_url += "/protocol/openid-connect/token";

        ResponseEntity<String> exchange = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
        TokenResponse tokenResponse = objectMapper.readValue(exchange.getBody().getBytes(), TokenResponse.class);

        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping(produces = "application/json", path = "configuration")
    public KeycloakConfiguration getConfiguration() {
        return keycloakConfiguration;
    }

    @GetMapping(produces = "application/json", path = "current")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return new User(jwt.getClaim("preferred_username"), authentication.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toSet()));
    }

    private String getUrlWithoutParameters(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return new URI(uri.getScheme(),
                uri.getAuthority(),
                uri.getPath(),
                null, // Ignore the query part of the input url
                uri.getFragment()).toString();
    }

}
