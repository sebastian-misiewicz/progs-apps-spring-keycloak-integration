package com.progsapps.springkeycloakintegration.controller;

import com.progsapps.springkeycloakintegration.model.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class SecurityController {

    public static Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    @GetMapping(produces = "application/json", path = "current")
    public User getUser() {
        LOGGER.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        KeycloakAuthenticationToken authentication =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();

        KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;

        return new User(kPrincipal.getKeycloakSecurityContext().getIdToken().getPreferredUsername(),
                kPrincipal.getKeycloakSecurityContext().getToken().getRealmAccess().getRoles());
    }

}
