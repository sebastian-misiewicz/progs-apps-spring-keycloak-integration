package com.progsapps.springkeycloakintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/product*", "/security/current*")
                .hasRole("progs-apps-regular-user")
                .anyRequest()
                .permitAll();
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.csrf().disable().build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(JwtRolesConverter jwtRolesConverter) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtRolesConverter);
        return jwtAuthenticationConverter;
    }

}
