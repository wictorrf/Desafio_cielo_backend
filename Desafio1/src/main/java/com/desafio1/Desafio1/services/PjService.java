package com.desafio1.Desafio1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio1.Desafio1.domains.Pj;
import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.repositories.PjRepository;
import com.desafio1.Desafio1.repositories.UserRepository;
import com.desafio1.Desafio1.services.responses.ResponsesReq;
import com.desafio1.Desafio1.services.validations.ValidationPj;

import jakarta.transaction.Transactional;

@Service
public class PjService {
    
    @Autowired
    private PjRepository pjRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<ResponsesReq<Optional<Pj>>> updateUserPj(Long id, UserDto data) {
        Optional<Pj> existingUserPjOptional = pjRepository.findById(id);
        Optional<User> existingUserOptional = userRepository.findUserByCpf(data.cpf());

        ResponseEntity<ResponsesReq<Optional<Pj>>> validationResponse = ValidationPj.validateUserDto(data);
        if (!validationResponse.getBody().isSuccess()) {
            return validationResponse;
        }
    
        if (existingUserPjOptional.isPresent() && existingUserOptional.isPresent()) {
            Pj existingUserPj = existingUserPjOptional.get();
            User existingUser = existingUserOptional.get();
            existingUserPj.setName(data.name());
            existingUserPj.setEmail(data.email());
            existingUserPj.setMcc(data.mcc());
            existingUserPj.setRazaoSocial(data.razaoSocial());
            existingUserPj.setCnpj(data.cnpj());

            existingUser.setName(data.name());
            existingUser.setEmail(data.email());
            existingUser.setMcc(data.mcc());

            pjRepository.save(existingUserPj);
            userRepository.save(existingUser);
            return ResponsesReq.success("Usuário atualizado com sucesso", Optional.of(existingUserPj));

        } else {
            return ResponsesReq.error("Cpf não encontrado no banco de dados");
        }
    }

    public List<Pj> getAllPj(){
        List<Pj> usersPj = this.pjRepository.findAll();
        return usersPj;
    }

    public ResponseEntity<ResponsesReq<Optional<Pj>>> deleteUserPj(String cpf){
        Optional<Pj> existingUserPj = pjRepository.findUserByCpf(cpf);
        Optional<User> existingUser = userRepository.findUserByCpf(cpf);
        if(existingUserPj.isPresent()){
            Pj userPj = existingUserPj.get();
            User user = existingUser.get();
            this.pjRepository.delete(userPj);
            this.userRepository.delete(user);
            return ResponsesReq.success("Usuário deletado com sucesso", Optional.of(userPj));
        }else{
            return ResponsesReq.error("Usuário não encontrado no banco de dados");
        }
    }
}
