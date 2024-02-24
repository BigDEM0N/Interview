package com.avgkin.tacocloudplusserver.controller;

import com.avgkin.tacocloudplusserver.entity.dto.LoginRequestDTO;
import com.avgkin.tacocloudplusserver.entity.dto.RegistrationRequestDTO;
import com.avgkin.tacocloudplusserver.entity.po.User;
import com.avgkin.tacocloudplusserver.service.UserService;
import com.avgkin.tacocloudplusserver.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

@RestController
@RequestMapping("/user")
public class UserController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,UserService userService,JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO requestDTO,HttpSession session){
        try{
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(),requestDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtil.getToken(userDetails.getUsername());
            return jwtToken;
        }catch (Exception e){
            return "error";
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequestDTO requestDTO){
        User user = userService.registerUser(requestDTO);
        return user;
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public boolean logout(HttpServletRequest request){

        return true;
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('USER')")
    public String check(){
        return "ok";
    }
}
