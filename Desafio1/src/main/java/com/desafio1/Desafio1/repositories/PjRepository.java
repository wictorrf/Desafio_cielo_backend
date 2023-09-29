package com.desafio1.Desafio1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio1.Desafio1.domains.Pj;

public interface PjRepository extends JpaRepository<Pj, Long> {

    Optional<Pj> findById(Long id);

    Optional<Pj> findUserByCpf(String cpf);
    
}
