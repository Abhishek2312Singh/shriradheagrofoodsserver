package com.srap.Shree.Radhe.Agro.Foods.controller;

import com.srap.Shree.Radhe.Agro.Foods.dto.ChangePasswordInputDto;
import com.srap.Shree.Radhe.Agro.Foods.dto.UserInputDto;
import com.srap.Shree.Radhe.Agro.Foods.service.UserPasswordService;
import com.srap.Shree.Radhe.Agro.Foods.service.UserService;
import com.srap.Shree.Radhe.Agro.Foods.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserPasswordService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> getToken(@RequestBody UserInputDto userInputDto, Principal principal){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userInputDto.getUsername()
                ,userInputDto.getPassword()));
        return ResponseEntity.ok(jwtUtils.generateToken(userInputDto.getUsername()));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordInputDto request,Principal principal){
        return ResponseEntity.ok(userService.changePassword(request,principal.getName()));
    }
}
