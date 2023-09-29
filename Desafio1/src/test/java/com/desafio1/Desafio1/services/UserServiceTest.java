package com.desafio1.Desafio1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.domains.domainsTypes.UserType;
import com.desafio1.Desafio1.repositories.PfRepository;
import com.desafio1.Desafio1.repositories.PjRepository;
import com.desafio1.Desafio1.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    UserService service;

    @Mock
    UserRepository userRepository;

    @Mock
    PfRepository pfRepository;

    @Mock
    PjRepository pjRepository;

    User user;

    @BeforeEach
    public void setUp(){
        user = new User(8l, "teste", "teste@email.com", "12312312333", "1111", UserType.PF);
    }

    @Test
    void deveBuscarTodosOsUsers(){
        when(userRepository.findAll()).thenReturn(List.of(user));

        assertEquals(List.of(user), service.getAllUsers());

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
}}
