package com.tutorial.oauth2.keycloak.controller;

import com.tutorial.oauth2.keycloak.dto.ResponseMessage;
import com.tutorial.oauth2.keycloak.model.User;
import com.tutorial.oauth2.keycloak.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserControler {



    @Autowired
    private KeycloakService keycloakService;


    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> create(@RequestBody User user){

        Object[] obj=keycloakService.createUser(user);

        int status = (int) obj[0];

        ResponseMessage message = (ResponseMessage) obj[1];

        return ResponseEntity.status(status).body(message);

    }


}
