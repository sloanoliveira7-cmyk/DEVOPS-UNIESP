package com.uniesp.desafio.domain;

/**
 * Serviço de domínio para operações matemáticas.
 * Implementa a lógica de negócio pura, sem dependências externas.
 */
public class Calculator {

    /**
     * Soma dois números inteiros.
     * Corrige o bug original da aplicação Go.
     * 
     * @param a primeiro número
     * @param b segundo número
     * @return soma de a e b
     */
    public int sum(int a, int b) {
        return a + b;
    }

    /**
     * Classifica um número em categorias: Grande, Médio ou Pequeno.
     * Implementa a lógica de negócio de classificação.
     * 
     * @param value valor a ser classificado
     * @return classificação do valor
     */
    public String classify(int value) {
        if (value > 10) {
            return "Grande";
        } else if (value > 5) {
            return "Medio";
        }
        return "Pequeno";
    }
}
