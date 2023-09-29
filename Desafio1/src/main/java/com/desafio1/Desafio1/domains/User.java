package com.desafio1.Desafio1.domains;


import com.desafio1.Desafio1.domains.domainsTypes.UserType;
import com.desafio1.Desafio1.dtos.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "campo name deve ser preenchido")
    private String name;

    @NotNull(message = "email deve ser preenchido")
    @Email(message = "campo de email esta invalido")
    private String email;

    @Column(unique = true)
    @NotNull(message = "campo cpf deve ser preenchido")
    @Size(min = 11, max = 11, message = "campo cpf esta incorreto")
    private String cpf;

    @NotNull(message = "campo mcc deve ser preenchido")
    @Size(max = 4, message = "mcc deve ter no maximo 4 caracteres")
    private String mcc;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDto data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.mcc = data.mcc();
        this.userType = data.userType();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", cpf=" + cpf + ", mcc=" + mcc
                + ", userType=" + userType + "]";
    }

    
}