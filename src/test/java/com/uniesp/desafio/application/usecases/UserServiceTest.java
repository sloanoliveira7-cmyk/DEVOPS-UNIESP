package com.uniesp.desafio.application.usecases;

import com.uniesp.desafio.application.ports.UserRepository;
import com.uniesp.desafio.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para UserService.
 * Implementa TDD com mocks para a porta UserRepository.
 */
public class UserServiceTest {
    
    private UserService userService;
    
    @Mock
    private UserRepository userRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }
    
    /**
     * Testa a criação de um usuário válido.
     */
    @Test
    public void testCreateUserSuccess() {
        User user = new User(null, "João Silva");
        User savedUser = new User(1L, "João Silva");
        
        when(userRepository.save(user)).thenReturn(savedUser);
        
        User result = userService.createUser(user);
        
        assertEquals(1L, result.getId());
        assertEquals("João Silva", result.getName());
        verify(userRepository, times(1)).save(user);
    }
    
    /**
     * Testa a criação de um usuário com nome vazio.
     */
    @Test
    public void testCreateUserInvalidEmptyName() {
        User user = new User(null, "");
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }
    
    /**
     * Testa a criação de um usuário com nome nulo.
     */
    @Test
    public void testCreateUserInvalidNullName() {
        User user = new User(null, null);
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }
    
    /**
     * Testa a busca de um usuário por ID.
     */
    @Test
    public void testGetUserByIdSuccess() {
        User user = new User(1L, "João Silva");
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        Optional<User> result = userService.getUserById(1L);
        
        assertTrue(result.isPresent());
        assertEquals("João Silva", result.get().getName());
        verify(userRepository, times(1)).findById(1L);
    }
    
    /**
     * Testa a busca de um usuário inexistente.
     */
    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        Optional<User> result = userService.getUserById(999L);
        
        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(999L);
    }
}
