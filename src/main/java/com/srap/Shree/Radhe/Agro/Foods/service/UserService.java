package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.config.SecurityConfig;
import com.srap.Shree.Radhe.Agro.Foods.dto.ChangePasswordInputDto;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found!!!"));
    }
}
