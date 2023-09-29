package com.desafio1.Desafio1.services.validations;

import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.services.responses.ResponsesReq;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationPf {

    public static <T> ResponseEntity<ResponsesReq<T>> validateUserDto(UserDto data) {

        if (data == null) {
            return ResponsesReq.error("Os dados do usuário não foram fornecidos.");
        }

        if (data.name() == null || data.name().isEmpty()) {
            return ResponsesReq.error("O campo 'name' deve ser preenchido.");
        }
        if(data.email() == null || data.email().isEmpty()){
            return ResponsesReq.error("O campo 'email' deve ser preenchido corretamente.");
        }
        if(data.cpf().length() != 11 || data.cpf().isEmpty()){
            return ResponsesReq.error("O campo 'cpf' deve ter 11 caracteres.");
        }
        if(data.mcc().length() != 4 || data.mcc().isEmpty()){
            return ResponsesReq.error("O campo 'mcc' deve ter 4 caracteres");
        }


        return ResponsesReq.success("Dados do usuário válidos", null);
    }
}
