package com.avgkin.tacocloudplusserver.controller;

import com.avgkin.tacocloudplusserver.entity.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO requestDTO){
        try{
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(),requestDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            System.out.println(authentication.getPrincipal());
            return "success";
        }catch (Exception e){
            return "error";
        }
    }
}
