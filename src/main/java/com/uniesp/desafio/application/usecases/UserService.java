package com.uniesp.desafio.application.usecases;

import com.uniesp.desafio.application.ports.UserRepository;
import com.uniesp.desafio.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Caso de uso para gerenciar usuários.
 * Implementa a lógica de aplicação, orquestrando operações de domínio.
 * Segue o princípio Single Responsibility Principle (SRP).
 */
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    /**
     * Cria um novo usuário.
     * 
     * @param user usuário a ser criado
     * @return usuário criado
     * @throws IllegalArgumentException se o usuário for inválido
     */
    public User createUser(User user) {
        if (!user.isValid()) {
            throw new IllegalArgumentException("Usuário inválido: nome não pode estar vazio");
        }
        return userRepository.save(user);
    }
    
    /**
     * Busca um usuário por ID.
     * 
     * @param id ID do usuário
     * @return Optional contendo o usuário se encontrado
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * Lista todos os usuários.
     * 
     * @return lista de usuários
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Deleta um usuário por ID.
     * 
     * @param id ID do usuário a ser deletado
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
