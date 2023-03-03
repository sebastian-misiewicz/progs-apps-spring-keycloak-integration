package com.progsapps.springkeycloakintegration.model;

public class KeycloakConfiguration {

    private final String issuerUri;

    private final String clientId;


    public KeycloakConfiguration(String issuerUri, String clientId) {
        this.issuerUri = issuerUri;
        this.clientId = clientId;
    }

    public String getIssuerUri() {
        return issuerUri;
    }

    public String getClientId() {
        return clientId;
    }
}
