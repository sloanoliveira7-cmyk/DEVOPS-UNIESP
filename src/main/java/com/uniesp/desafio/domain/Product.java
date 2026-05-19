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

        BigDecimal finalPrice = basePrice;

        if ("Notebook".equalsIgnoreCase(name)) {
            finalPrice = basePrice.multiply(new BigDecimal("0.9")); 
        } else if ("Mouse".equalsIgnoreCase(name)) {
            finalPrice = basePrice.multiply(new BigDecimal("0.95"));
        }

        // Isso corrige o erro: força 2 casas decimais e arredonda se necessário
        return finalPrice.setScale(2, java.math.RoundingMode.HALF_UP);
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
