package com.desafio1.Desafio1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio1.Desafio1.domains.Pj;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.services.PjService;
import com.desafio1.Desafio1.services.responses.ResponsesReq;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/pj")
public class PjController {
    
    @Autowired
    private PjService pjService;

    @GetMapping("/users")
    public ResponseEntity<List<Pj>> getAllPj(){
        List<Pj> listpj = this.pjService.getAllPj();
        return ResponseEntity.status(HttpStatus.OK).body(listpj);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponsesReq<Optional<Pj>>> updatePj(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto data){
        try {
            return pjService.updateUserPj(id, data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<ResponsesReq<Optional<Pj>>> deletePj(@PathVariable(value = "cpf") String cpf){
        try {
            return pjService.deleteUserPj(cpf);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }
}
