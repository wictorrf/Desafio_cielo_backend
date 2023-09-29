package com.desafio1.Desafio1.domains;

import com.desafio1.Desafio1.dtos.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "pf")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "campo name deve ser preenchido")
    @Size(min = 1, message = "campo name deve ser preenchido")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "campo email deve ser preenchido")
    @Email(message = "campo email esta invalido")
    private String email;

    @Column(length = 14, nullable = false, unique = true)
    @Size(min = 11, max = 11, message = "campo cpf esta incorreto")
    private String cpf;

    @Column(length = 4, nullable = false)
    @NotNull(message = "campo mcc deve ser preenchido")
    @Size(max = 4, message = "mcc deve ter no maximo 4 caracteres")
    private String mcc;

    public Pf(UserDto data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.mcc = data.mcc();
    }
}