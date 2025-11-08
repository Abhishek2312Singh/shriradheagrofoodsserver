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
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String,String>> changePassword(@RequestBody ChangePasswordInputDto request,Principal principal){
        Map<String, String> response = new HashMap<>();

        try {
            String message = userService.changePassword(request, principal.getName());
            response.put("status", "success");
            response.put("message", message);
            return ResponseEntity.ok(response);

        } catch (ResponseStatusException ex) {
            // Handle specific error from your service
            response.put("status", "fail");
            response.put("message", ex.getReason()); // “Current Password Is Incorrect” or “Password Does Not Match”
            return ResponseEntity.status(ex.getStatusCode()).body(response);

        } catch (Exception ex) {
            // Handle unexpected errors
            response.put("status", "fail");
            response.put("message", ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
