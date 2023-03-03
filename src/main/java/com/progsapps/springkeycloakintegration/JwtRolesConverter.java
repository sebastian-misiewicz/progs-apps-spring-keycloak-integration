package com.progsapps.springkeycloakintegration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;


@Component
public class JwtRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        source.getClaimAsMap("realm_access").forEach((key, value) -> {
            JSONArray roles = (JSONArray) value;
            for (Object role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        });

        return grantedAuthorities;
    }
}
