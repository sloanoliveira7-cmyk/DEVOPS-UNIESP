package com.uniesp.desafio.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para ProductEntity.
 * Fornece operações CRUD básicas através do Spring Data JPA.
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
