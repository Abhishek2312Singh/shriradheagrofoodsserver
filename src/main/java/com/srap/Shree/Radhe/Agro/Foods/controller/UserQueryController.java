package com.srap.Shree.Radhe.Agro.Foods.controller;

import com.srap.Shree.Radhe.Agro.Foods.dto.ChangePasswordInputDto;
import com.srap.Shree.Radhe.Agro.Foods.dto.UserQueryInputDto;
import com.srap.Shree.Radhe.Agro.Foods.dto.UserQueryOutputDto;
import com.srap.Shree.Radhe.Agro.Foods.entity.UserQuery;
import com.srap.Shree.Radhe.Agro.Foods.service.UserQueryImpl;
import com.srap.Shree.Radhe.Agro.Foods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://shriradheagrofoods.com"})
public class UserQueryController {
    @Autowired
    private UserQueryImpl userQuery;

    @GetMapping("/getdata")
    public ResponseEntity<List<UserQueryOutputDto>> getAllQuery(){
        return ResponseEntity.ok(userQuery.getAllQuery());
    }
    @PostMapping("/add")
    public String addUserQuery(@RequestBody UserQueryInputDto userQueryInputDto){
        return userQuery.addUserQuery(userQueryInputDto);
    }
}
