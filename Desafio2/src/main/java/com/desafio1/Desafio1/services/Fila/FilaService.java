package com.desafio1.Desafio1.services.Fila;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.services.UserService;

@Service
public class FilaService {
    
    @Autowired
    private Fila fila;

    @Autowired
    private UserService userService;

    private Set<Long> userIdsInQueue = new HashSet<>();

    public String filaUsers(){
        for (User user : userService.getAllUsers()) {
            if (!userIdsInQueue.contains(user.getId())) {
                fila.enqueue(user);
                userIdsInQueue.add(user.getId());
            }
        }
        return fila.toString();
    }
}
