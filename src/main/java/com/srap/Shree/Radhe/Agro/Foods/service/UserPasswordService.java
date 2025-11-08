package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.config.SecurityConfig;
import com.srap.Shree.Radhe.Agro.Foods.dto.ChangePasswordInputDto;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserPasswordService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityConfig config;

    public String changePassword(ChangePasswordInputDto changePasswordReq, String username){
        User user = userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        if(!config.encoder().matches(changePasswordReq.getCurrentPassword(),user.getPassword())){
            throw new RuntimeException("Current Password Is Incorrect");
        }
        if(!changePasswordReq.getNewPassword().equals(changePasswordReq.getConfirmPassword())){
            throw new RuntimeException("Password Does Not Match");
        }
        user.setPassword(config.encoder().encode(changePasswordReq.getNewPassword()));
        userRepo.save(user);
        return "Password Changed Successfully!!";
    }
}
