package com.uniesp.desafio.application.ports;

import com.uniesp.desafio.domain.Product;
import java.util.List;
import java.util.Optional;

/**
 * Porta (interface) para persistência de produtos.
 * Define o contrato que adaptadores de persistência devem implementar.
 */
public interface ProductRepository {
    
    /**
     * Salva um produto.
     * 
     * @param product produto a ser salvo
     * @return produto salvo com ID gerado
     */
    Product save(Product product);
    
    /**
     * Busca um produto por ID.
     * 
     * @param id ID do produto
     * @return Optional contendo o produto se encontrado
     */
    Optional<Product> findById(Long id);
    
    /**
     * Lista todos os produtos.
     * 
     * @return lista de produtos
     */
    List<Product> findAll();
    
    /**
     * Deleta um produto por ID.
     * 
     * @param id ID do produto a ser deletado
     */
    void deleteById(Long id);
}
