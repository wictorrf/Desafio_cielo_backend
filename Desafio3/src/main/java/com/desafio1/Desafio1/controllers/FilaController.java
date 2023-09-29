package com.desafio1.Desafio1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio1.Desafio1.services.Fila.Fila;
import com.desafio1.Desafio1.services.Fila.FilaService;

@RestController
@RequestMapping("/fila")
public class FilaController {
    
    @Autowired
    private FilaService filaService;

    @Autowired
    private Fila fila;

    @GetMapping
    public Object filaUSer(){
        return this.filaService.filaUsers();
    }

    @GetMapping("/first")
    public Object firstUser(){
        return this.fila.first();
    }
}
