package com.uniesp.desafio.infrastructure.persistence;

import com.uniesp.desafio.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA para persistência de usuários.
 * Adaptador que implementa a porta UserRepository.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    /**
     * Converte a entidade JPA para o domínio.
     * 
     * @return objeto de domínio User
     */
    public User toDomain() {
        return new User(id, name);
    }
    
    /**
     * Cria uma entidade JPA a partir do domínio.
     * 
     * @param user objeto de domínio
     * @return entidade JPA
     */
    public static UserEntity fromDomain(User user) {
        return new UserEntity(user.getId(), user.getName());
    }
}
