package com.desafio1.Desafio1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio1.Desafio1.domains.Pf;
import com.desafio1.Desafio1.domains.Pj;
import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.domains.domainsTypes.UserType;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.repositories.PfRepository;
import com.desafio1.Desafio1.repositories.PjRepository;
import com.desafio1.Desafio1.repositories.UserRepository;
import com.desafio1.Desafio1.services.responses.ResponsesReq;
import com.desafio1.Desafio1.services.validations.ValidationPf;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PfRepository pfRepository;

    @Autowired
    private PjRepository pjRepository;

    @Autowired
    private ValidationPf validations;

    @Transactional
    public ResponseEntity<ResponsesReq<User>> registerUser( UserDto data) {
        Optional<User> existingUser = userRepository.findUserByCpf(data.cpf());

        ResponseEntity<ResponsesReq<User>> validationResponse = ValidationPf.validateUserDto(data);
        if (!validationResponse.getBody().isSuccess()) {
            return validationResponse;
        }
        if (existingUser.isPresent()) {
            return ResponsesReq.error("Usuario ja existente com esse cpf!");
        }
        User newUser;
        if (data.userType() == UserType.PF) {
            newUser = registerPf(data);
        } else if (data.userType() == UserType.PJ) {
            newUser = registerPj(data);
        } else {
            return ResponsesReq.error("Tipo de usuário desconhecido: " + data.userType());
        }

        return ResponsesReq.success("Usuário registrado com sucesso", newUser);
    }

    public List<User> getAllUsers(){
        List<User> listUsers = this.userRepository.findAll();
        return listUsers;
    }

    public ResponseEntity<ResponsesReq<Optional<User>>> findById(Long id) {
        Optional<User> existUser = userRepository.findById(id);
        if (!existUser.isPresent()) {
            return ResponsesReq.error("Usuario nao cadastrado no banco de dados");
        } else {
            return ResponsesReq.success(null, existUser);
        }
    }    
    
    private User createUserFromData(UserDto data){
        User newUser = new User();
        newUser.setName(data.name());
        newUser.setEmail(data.email());
        newUser.setCpf(data.cpf());
        newUser.setMcc(data.mcc());
        newUser.setUserType(data.userType());
        return newUser;
    }

    private User registerPf(UserDto data){
        Pf newPf = new Pf(data);
        User newUser = createUserFromData(data);
        pfRepository.save(newPf);
        userRepository.save(newUser);
        return newUser;
    }

    private User registerPj(UserDto data){
        Pj newPj = new Pj(data);
        User newUser = createUserFromData(data);
        pjRepository.save(newPj);
        userRepository.save(newUser);
        return newUser;
    }
}
