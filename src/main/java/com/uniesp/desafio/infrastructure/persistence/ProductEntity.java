package com.uniesp.desafio.infrastructure.persistence;

import com.uniesp.desafio.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Entidade JPA para persistência de produtos.
 * Adaptador que implementa a porta ProductRepository.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;
    
    /**
     * Converte a entidade JPA para o domínio.
     * 
     * @return objeto de domínio Product
     */
    public Product toDomain() {
        return new Product(id, name, basePrice);
    }
    
    /**
     * Cria uma entidade JPA a partir do domínio.
     * 
     * @param product objeto de domínio
     * @return entidade JPA
     */
    public static ProductEntity fromDomain(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getBasePrice());
    }
}
