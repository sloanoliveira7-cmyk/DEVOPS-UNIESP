package com.uniesp.desafio.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para UserEntity.
 * Fornece operações CRUD básicas através do Spring Data JPA.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
