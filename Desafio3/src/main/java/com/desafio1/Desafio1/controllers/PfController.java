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

import com.desafio1.Desafio1.domains.Pf;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.services.PfService;
import com.desafio1.Desafio1.services.responses.ResponsesReq;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pf")
public class PfController {
    
    @Autowired
    private PfService pfService;

    @GetMapping("/users")
    public ResponseEntity<List<Pf>> getAllPf(){
        List<Pf> list = this.pfService.getAllPf();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponsesReq<Optional<Pf>>> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto data){
        try {
            return pfService.updateUserPf(id, data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<ResponsesReq<Optional<Pf>>> deleteUser(@PathVariable(value = "cpf") String cpf){
        try {
            return pfService.deleteUserPf(cpf);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }
}
