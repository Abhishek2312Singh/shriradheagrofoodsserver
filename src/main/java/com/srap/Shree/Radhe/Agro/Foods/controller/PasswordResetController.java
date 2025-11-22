package com.srap.Shree.Radhe.Agro.Foods.controller;

import com.srap.Shree.Radhe.Agro.Foods.config.SecurityConfig;
import com.srap.Shree.Radhe.Agro.Foods.entity.PasswordResetToken;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.PasswordResetTokenRepo;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import com.srap.Shree.Radhe.Agro.Foods.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private PasswordResetTokenRepo tokenRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityConfig config;

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(String email){
        return ResponseEntity.ok(passwordResetTokenService.generateToken(email));
    }
    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String newPassword,
                                                @RequestParam String confirmPassword){
        PasswordResetToken resetToken = tokenRepo.findByToken(token);
        if(resetToken == null ){
            return ResponseEntity.badRequest().body("Invalid Token");
        }
        if(resetToken.getExpiryTime().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("Link Expired");
        }
        if(!newPassword.equals(confirmPassword)){
            return ResponseEntity.badRequest().body("Confirm Password Not Matched!!");
        }
        User user = resetToken.getUser();
        user.setPassword(config.encoder().encode(newPassword));
        userRepo.save(user);
        return ResponseEntity.ok("Password Changed Successfully");
    }
}
