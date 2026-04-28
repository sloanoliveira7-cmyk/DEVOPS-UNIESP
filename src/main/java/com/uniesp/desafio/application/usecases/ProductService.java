package com.uniesp.desafio.application.usecases;

import com.uniesp.desafio.application.ports.ProductRepository;
import com.uniesp.desafio.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Caso de uso para gerenciar produtos.
 * Implementa a lógica de aplicação, orquestrando operações de domínio.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    
    /**
     * Cria um novo produto.
     * 
     * @param product produto a ser criado
     * @return produto criado
     * @throws IllegalArgumentException se o produto for inválido
     */
    public Product createProduct(Product product) {
        if (!product.isValid()) {
            throw new IllegalArgumentException("Produto inválido: nome e preço são obrigatórios");
        }
        return productRepository.save(product);
    }
    
    /**
     * Busca um produto por ID.
     * 
     * @param id ID do produto
     * @return Optional contendo o produto se encontrado
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    /**
     * Lista todos os produtos.
     * 
     * @return lista de produtos
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Deleta um produto por ID.
     * 
     * @param id ID do produto a ser deletado
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
