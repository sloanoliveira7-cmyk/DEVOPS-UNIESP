package com.uniesp.desafio.application.ports;

import com.uniesp.desafio.domain.User;
import java.util.List;
import java.util.Optional;

/**
 * Porta (interface) para persistência de usuários.
 * Define o contrato que adaptadores de persistência devem implementar.
 * Segue o princípio Dependency Inversion Principle (DIP).
 */
public interface UserRepository {
    
    /**
     * Salva um usuário.
     * 
     * @param user usuário a ser salvo
     * @return usuário salvo com ID gerado
     */
    User save(User user);
    
    /**
     * Busca um usuário por ID.
     * 
     * @param id ID do usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<User> findById(Long id);
    
    /**
     * Lista todos os usuários.
     * 
     * @return lista de usuários
     */
    List<User> findAll();
    
    /**
     * Deleta um usuário por ID.
     * 
     * @param id ID do usuário a ser deletado
     */
    void deleteById(Long id);
}
