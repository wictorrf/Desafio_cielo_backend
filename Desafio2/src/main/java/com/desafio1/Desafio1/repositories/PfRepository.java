package com.desafio1.Desafio1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio1.Desafio1.domains.Pf;

public interface PfRepository extends JpaRepository<Pf, Long> {

    Optional<Pf> findById(Long id);
    Optional<Pf> findUserByCpf(String cpf);
    
}
