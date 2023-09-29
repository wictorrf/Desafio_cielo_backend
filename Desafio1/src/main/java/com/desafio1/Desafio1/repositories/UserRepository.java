package com.desafio1.Desafio1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio1.Desafio1.domains.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
   User findByEmail(String email);
   Optional<User> findById(Long id);
   Optional<User> findUserByCpf(String cpf);
}
