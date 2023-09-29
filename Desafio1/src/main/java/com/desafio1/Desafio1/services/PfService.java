package com.desafio1.Desafio1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio1.Desafio1.domains.Pf;
import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.repositories.PfRepository;
import com.desafio1.Desafio1.repositories.UserRepository;
import com.desafio1.Desafio1.services.responses.ResponsesReq;
import com.desafio1.Desafio1.services.validations.ValidationPf;

import jakarta.transaction.Transactional;

@Service
public class PfService {
    
    @Autowired
    private PfRepository pfRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<ResponsesReq<Optional<Pf>>> updateUserPf(Long id, UserDto data) {
        Optional<Pf> existingUserPfOptional = pfRepository.findById(id);
        Optional<User> existingUserOptional = userRepository.findUserByCpf(data.cpf());

        ResponseEntity<ResponsesReq<Optional<Pf>>> validationResponse = ValidationPf.validateUserDto(data);
        if (!validationResponse.getBody().isSuccess()) {
            return validationResponse;
        }
    
        if (existingUserPfOptional.isPresent() && existingUserOptional.isPresent()) {
            Pf existingUserPf = existingUserPfOptional.get();
            User existingUser = existingUserOptional.get();

            existingUserPf.setName(data.name());
            existingUserPf.setEmail(data.email());
            existingUserPf.setMcc(data.mcc());

            existingUser.setName(data.name());
            existingUser.setEmail(data.email());
            existingUser.setMcc(data.mcc());
            
            pfRepository.save(existingUserPf);
            userRepository.save(existingUser);
            return ResponsesReq.success("Usuário atualizado com sucesso", Optional.of(existingUserPf));

        } else {
            return ResponsesReq.error("Cpf não encontrado no banco de dados");
        }
    }

    public List<Pf> getAllPf(){
        List<Pf> usersPf = this.pfRepository.findAll();
        return usersPf;
    }

    public ResponseEntity<ResponsesReq<Optional<Pf>>> deleteUserPf(String cpf){
        Optional<Pf> existingUserPfOptional = pfRepository.findUserByCpf(cpf);
        Optional<User> existingUserOptional = userRepository.findUserByCpf(cpf);
        if(existingUserPfOptional.isPresent()){
            Pf existingUserPf = existingUserPfOptional.get();
            User existingUser = existingUserOptional.get();
            this.pfRepository.delete(existingUserPf);
            this.userRepository.delete(existingUser);
            return ResponsesReq.success("Usuário deletado com sucesso", Optional.of(existingUserPf));
        }else{
            return ResponsesReq.error("Usuário não encontrado no banco de dados");
        }
    }
}
