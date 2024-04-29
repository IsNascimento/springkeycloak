package com.example.springkeycloak.controller;

import com.example.springkeycloak.records.User;
import com.example.springkeycloak.service.KeycloakIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class keycloakRestController {

    private final KeycloakIntegrationService keycloakIntegrationService;

    @PostMapping("auth")
    public ResponseEntity<?> authneticate(@RequestBody User user) {
        return keycloakIntegrationService.authenticateOnKeycloak(user);
    }

    @GetMapping("/perfis/publico")
    public ResponseEntity<?> publicEndPoint() {
        return new ResponseEntity<>("Endpoint publico retornando pra todo mundo", HttpStatus.OK);
    }

    @GetMapping("/perfis/admin-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminEndPoint(@AuthenticationPrincipal Jwt jwt) {
        String user= jwt.getClaim("given_name");
        return new ResponseEntity<>("Olá " + user + ". Este é o endpoint administrativo retornando pra administradores", HttpStatus.OK);
    }

    @GetMapping("/perfis/operador-role")
    @PreAuthorize("hasRole('OPER')")
    public ResponseEntity<?> operadorEndPoint() {
        return new ResponseEntity<>("Endpoint operacional retornando pra todo operadores", HttpStatus.OK);
    }

}
