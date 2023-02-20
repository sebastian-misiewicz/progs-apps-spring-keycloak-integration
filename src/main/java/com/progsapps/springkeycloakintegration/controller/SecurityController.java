package com.progsapps.springkeycloakintegration.controller;

import com.progsapps.springkeycloakintegration.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class SecurityController {

    public static Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    private final OAuth2AuthorizedClient oAuth2AuthorizedClient;

    public SecurityController(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager, OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
        this.oAuth2AuthorizedClient = oAuth2AuthorizedClient;
    }


    @GetMapping(produces = "application/json", path = "current")
    public User getUser(Authentication auth) {
        OidcUser principal = (OidcUser) auth.getPrincipal();
        oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withAuthorizedClient(oAuth2AuthorizedClient).build());
        return null;
    }

}
