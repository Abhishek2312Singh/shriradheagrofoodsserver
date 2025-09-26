package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.config.SecurityConfig;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminUser {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecurityConfig config;
    @Bean
    public CommandLineRunner createAdminTesting(){
        return args -> {
            if(userRepo.findByUsername("admin").isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(config.encoder().encode("root"));
                userRepo.save(user);
                System.out.println("Admin Created Successfully!!!!");
            }
        };
    }
}
