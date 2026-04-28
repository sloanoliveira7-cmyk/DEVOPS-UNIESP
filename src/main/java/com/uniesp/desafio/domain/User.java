package com.uniesp.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de domínio User.
 * Representa um usuário no sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;

    /**
     * Valida se o usuário é válido.
     * 
     * @return true se o usuário é válido, false caso contrário
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty();
    }
}
