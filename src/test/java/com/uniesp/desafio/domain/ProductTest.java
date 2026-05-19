package com.uniesp.desafio.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Product.
 * Implementa TDD (Test Driven Development).
 */
public class ProductTest {
    
    /**
     * Testa o cálculo de preço final para Notebook com 10% de desconto.
     */
    @Test
    public void testCalculateFinalPriceNotebook() {
        Product product = new Product(1L, "Notebook", new BigDecimal("100.00"));
        BigDecimal result = product.calculateFinalPrice();
        assertEquals(new BigDecimal("90.00"), result, "Notebook deve ter 10% de desconto");
    }
    
    /**
     * Testa o cálculo de preço final para Mouse com 5% de desconto.
     */
    @Test
    public void testCalculateFinalPriceMouse() {
        Product product = new Product(1L, "Mouse", new BigDecimal("100.00"));
        BigDecimal result = product.calculateFinalPrice();
        assertEquals(new BigDecimal("95.00"), result, "Mouse deve ter 5% de desconto");
    }
    
    /**
     * Testa o cálculo de preço final para produto sem desconto.
     */
    @Test
    public void testCalculateFinalPriceNoDiscount() {
        Product product = new Product(1L, "Teclado", new BigDecimal("100.00"));
        BigDecimal result = product.calculateFinalPrice();
        assertEquals(new BigDecimal("100.00"), result, "Teclado não deve ter desconto");
    }
    
    /**
     * Testa o cálculo de preço final com preço nulo.
     */
    @Test
    public void testCalculateFinalPriceNull() {
        Product product = new Product(1L, "Produto", null);
        BigDecimal result = product.calculateFinalPrice();
        assertEquals(BigDecimal.ZERO, result, "Preço nulo deve retornar zero");
    }
    
    /**
     * Testa a validação de produto válido.
     */
    @Test
    public void testIsValidProduct() {
        Product product = new Product(1L, "Notebook", new BigDecimal("100.00"));
        assertTrue(product.isValid(), "Produto com nome e preço deve ser válido");
    }
    
    /**
     * Testa a validação de produto com nome vazio.
     */
    @Test
    public void testIsInvalidProductEmptyName() {
        Product product = new Product(1L, "", new BigDecimal("100.00"));
        assertFalse(product.isValid(), "Produto com nome vazio deve ser inválido");
    }
    
    /**
     * Testa a validação de produto com preço nulo.
     */
    @Test
    public void testIsInvalidProductNullPrice() {
        Product product = new Product(1L, "Notebook", null);
        assertFalse(product.isValid(), "Produto com preço nulo deve ser inválido");
    }
    
    /**
     * Testa a validação de produto com preço zero.
     */
    @Test
    public void testIsInvalidProductZeroPrice() {
        Product product = new Product(1L, "Notebook", BigDecimal.ZERO);
        assertFalse(product.isValid(), "Produto com preço zero deve ser inválido");
    }
}
