package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.dto.UserQueryInputDto;
import com.srap.Shree.Radhe.Agro.Foods.dto.UserQueryOutputDto;
import com.srap.Shree.Radhe.Agro.Foods.entity.UserQuery;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQueryImpl {
    @Autowired
    private UserQueryRepository userQueryRepository;

    public String addUserQuery(UserQueryInputDto userQueryInputDto) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserName(userQueryInputDto.getUserName());
        userQuery.setEmail(userQueryInputDto.getEmail());
        userQuery.setContact(userQueryInputDto.getContact());
        userQuery.setMessage(userQueryInputDto.getMessage());

        userQueryRepository.save(userQuery);
        return "Thank You!! We will get back to you soon";
    }

    public List<UserQueryOutputDto> getAllQuery(){
        List<UserQuery> userQueries = userQueryRepository.findAll();
        List<UserQueryOutputDto> userQueryOutputDtos = new ArrayList<>();

        for(UserQuery query : userQueries){
            UserQueryOutputDto userQueryOutputDto = new UserQueryOutputDto();
            userQueryOutputDto.setId(query.getId());
            userQueryOutputDto.setUserName(query.getUserName());
            userQueryOutputDto.setMessage(query.getMessage());
            userQueryOutputDto.setContact(query.getContact());
            userQueryOutputDto.setEmail(query.getEmail());

            userQueryOutputDtos.add(userQueryOutputDto);
        }
        return userQueryOutputDtos;
    }
}
