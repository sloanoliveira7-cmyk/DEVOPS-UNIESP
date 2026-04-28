package com.uniesp.desafio.presentation;

import com.uniesp.desafio.application.usecases.ProductService;
import com.uniesp.desafio.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gerenciar produtos.
 * Adaptador de apresentação (porta HTTP).
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    /**
     * Cria um novo produto.
     * 
     * @param product produto a ser criado
     * @return produto criado com status 201
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product created = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Busca um produto por ID.
     * 
     * @param id ID do produto
     * @return produto encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> {
                    product.setBasePrice(product.calculateFinalPrice());
                    return ResponseEntity.ok(product);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Lista todos os produtos com preços finais calculados.
     * 
     * @return lista de produtos
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        products.forEach(p -> p.setBasePrice(p.calculateFinalPrice()));
        return ResponseEntity.ok(products);
    }
    
    /**
     * Deleta um produto por ID.
     * 
     * @param id ID do produto a ser deletado
     * @return status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
