package com.example.springkeycloak.service;

import com.example.springkeycloak.records.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakIntegrationService {

    private final String CLIENT_ID = "realmbrabo_client";
    private final String GRANT_TYPE = "password";
    private final String KEYCLOAK_URL = "http://localhost:8080/realms/realmbrabo/protocol/openid-connect/token";

    public ResponseEntity<String> authenticateOnKeycloak(User user) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var formData = new LinkedMultiValueMap<String, String>();
        formData.add("client_id", CLIENT_ID);
        formData.add("username", user.username());
        formData.add("password", user.password());
        formData.add("grant_type", GRANT_TYPE);

        var httpEntity = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        return new RestTemplate().postForEntity(KEYCLOAK_URL, httpEntity, String.class);
    }
}
