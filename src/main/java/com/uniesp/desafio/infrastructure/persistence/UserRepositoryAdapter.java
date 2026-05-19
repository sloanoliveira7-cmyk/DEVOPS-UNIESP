package com.uniesp.desafio.infrastructure.persistence;

import com.uniesp.desafio.application.ports.UserRepository;
import com.uniesp.desafio.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa a porta UserRepository.
 * Conecta a camada de aplicação com a camada de persistência JPA.
 * Segue o padrão Adapter da Arquitetura Hexagonal.
 */
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    
    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity saved = userJpaRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserEntity::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(UserEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
