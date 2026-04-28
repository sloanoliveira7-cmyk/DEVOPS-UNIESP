package com.uniesp.desafio.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para verificação de saúde da aplicação.
 * Adaptador de apresentação (porta HTTP).
 */
@RestController
@RequestMapping("/health")
public class HealthController {
    
    /**
     * Endpoint para verificar a saúde da aplicação.
     * Retorna HTTP 200 se a aplicação está operante.
     * 
     * @return resposta com status 200 OK
     */
    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("Sistema Operante");
    }
}
