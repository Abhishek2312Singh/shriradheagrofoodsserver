package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.config.SecurityConfig;
import com.srap.Shree.Radhe.Agro.Foods.dto.ChangePasswordInputDto;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityConfig config;
    public String changePassword(ChangePasswordInputDto changePasswordReq, String username){
        User user = userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        if(!config.encoder().matches(user.getPassword(),changePasswordReq.getOldPass())){
            throw new RuntimeException("Old Password is Incorrect!!");
        }
        if(!changePasswordReq.getNewPass().equals(changePasswordReq.getConfPass())){
            throw new RuntimeException("New Password does not match!!");
        }
        user.setPassword(config.encoder().encode(changePasswordReq.getNewPass()));
        userRepo.save(user);
        return "Password Changed Successfully!!";
    }
}
