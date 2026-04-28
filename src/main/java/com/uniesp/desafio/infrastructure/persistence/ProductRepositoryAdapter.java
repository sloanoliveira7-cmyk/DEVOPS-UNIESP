package com.uniesp.desafio.infrastructure.persistence;

import com.uniesp.desafio.application.ports.ProductRepository;
import com.uniesp.desafio.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa a porta ProductRepository.
 * Conecta a camada de aplicação com a camada de persistência JPA.
 */
@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    
    private final ProductJpaRepository productJpaRepository;
    
    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductEntity.fromDomain(product);
        ProductEntity saved = productJpaRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(ProductEntity::toDomain);
    }
    
    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
}
