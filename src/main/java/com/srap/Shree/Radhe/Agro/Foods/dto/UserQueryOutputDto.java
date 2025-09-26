package com.srap.Shree.Radhe.Agro.Foods.dto;

import lombok.Data;

@Data
public class UserQueryOutputDto {
    private Long id;

    private String userName;
    private Long contact;
    private String email;
    private String message;
}
