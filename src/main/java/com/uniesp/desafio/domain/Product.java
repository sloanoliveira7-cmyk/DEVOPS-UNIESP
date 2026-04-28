package com.uniesp.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Entidade de domínio Product.
 * Representa um produto no sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private BigDecimal basePrice;

    /**
     * Calcula o preço final aplicando descontos específicos por produto.
     * Segue o princípio Single Responsibility Principle (SRP).
     * 
     * @return preço final com desconto aplicado
     */
    public BigDecimal calculateFinalPrice() {
        if (basePrice == null) {
            return BigDecimal.ZERO;
        }

        if ("Notebook".equalsIgnoreCase(name)) {
            return basePrice.multiply(new BigDecimal("0.9")); // 10% de desconto
        } else if ("Mouse".equalsIgnoreCase(name)) {
            return basePrice.multiply(new BigDecimal("0.95")); // 5% de desconto
        }

        return basePrice;
    }

    /**
     * Valida se o produto é válido.
     * 
     * @return true se o produto é válido, false caso contrário
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && basePrice != null && basePrice.compareTo(BigDecimal.ZERO) > 0;
    }
}
