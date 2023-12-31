package com.desafio1.Desafio1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.dtos.UserDto;
import com.desafio1.Desafio1.services.UserService;
import com.desafio1.Desafio1.services.responses.ResponsesReq;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/desafio")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponsesReq<User>> createUser(@RequestBody @Valid UserDto data) {
        try {
            return userService.registerUser(data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){
        List<User> listUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponsesReq<Optional<User>>> getUser(@PathVariable(value = "id") Long id) {
        try {
            return userService.findById(id);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponsesReq<>(false, errorMessage, null));
        }
    }
}