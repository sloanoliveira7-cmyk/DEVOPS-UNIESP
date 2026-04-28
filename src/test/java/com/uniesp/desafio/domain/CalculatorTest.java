package com.uniesp.desafio.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Calculator.
 * Implementa TDD (Test Driven Development).
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }
    
    /**
     * Testa a função sum com valores positivos.
     * Corrige o bug original da aplicação Go.
     */
    @Test
    public void testSumPositiveNumbers() {
        int result = calculator.sum(2, 3);
        assertEquals(5, result, "Soma de 2 + 3 deve ser 5");
    }
    
    /**
     * Testa a função sum com valores negativos.
     */
    @Test
    public void testSumNegativeNumbers() {
        int result = calculator.sum(-2, -3);
        assertEquals(-5, result, "Soma de -2 + -3 deve ser -5");
    }
    
    /**
     * Testa a função sum com valores mistos.
     */
    @Test
    public void testSumMixedNumbers() {
        int result = calculator.sum(10, -5);
        assertEquals(5, result, "Soma de 10 + -5 deve ser 5");
    }
    
    /**
     * Testa a função sum com zero.
     */
    @Test
    public void testSumWithZero() {
        int result = calculator.sum(0, 5);
        assertEquals(5, result, "Soma de 0 + 5 deve ser 5");
    }
    
    /**
     * Testa a função classify para valor Grande.
     */
    @Test
    public void testClassifyGrande() {
        String result = calculator.classify(15);
        assertEquals("Grande", result, "Valor 15 deve ser classificado como Grande");
    }
    
    /**
     * Testa a função classify para valor Médio.
     */
    @Test
    public void testClassifyMedio() {
        String result = calculator.classify(7);
        assertEquals("Medio", result, "Valor 7 deve ser classificado como Medio");
    }
    
    /**
     * Testa a função classify para valor Pequeno.
     */
    @Test
    public void testClassifyPequeno() {
        String result = calculator.classify(3);
        assertEquals("Pequeno", result, "Valor 3 deve ser classificado como Pequeno");
    }
    
    /**
     * Testa a função classify para valor limite (5).
     */
    @Test
    public void testClassifyBoundaryFive() {
        String result = calculator.classify(5);
        assertEquals("Pequeno", result, "Valor 5 deve ser classificado como Pequeno");
    }
    
    /**
     * Testa a função classify para valor limite (10).
     */
    @Test
    public void testClassifyBoundaryTen() {
        String result = calculator.classify(10);
        assertEquals("Medio", result, "Valor 10 deve ser classificado como Medio");
    }
}
