package com.desafio1.Desafio1.dtos;

import com.desafio1.Desafio1.domains.domainsTypes.UserType;

public record UserDto(String name, String email, String cpf, String mcc, UserType userType, String razaoSocial, String cnpj) {
    
}
