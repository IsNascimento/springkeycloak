package com.example.springkeycloak;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Definição para documentação com o swagger
@OpenAPIDefinition(info = @Info(title = "Springboot keycloak API", version = "1.0", description = "Aplicação de referência para autenticação e autorização utilizando o keycloak."))
public class SpringkeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringkeycloakApplication.class, args);
    }

}
