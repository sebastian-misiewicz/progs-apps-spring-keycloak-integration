package com.progsapps.springkeycloakintegration.model;

public class TokenRequest {
    private final String code;

    private final String grant_type;

    private final String client_id;

    public TokenRequest(String code, String grantType, String clientId) {
        this.code = code;
        this.grant_type = grantType;
        this.client_id = clientId;
    }

    public String getCode() {
        return code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public String getClient_id() {
        return client_id;
    }
}
