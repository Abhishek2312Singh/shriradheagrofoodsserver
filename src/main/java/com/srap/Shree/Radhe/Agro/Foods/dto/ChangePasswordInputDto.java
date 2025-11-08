package com.srap.Shree.Radhe.Agro.Foods.dto;

import lombok.Data;

@Data
public class ChangePasswordInputDto {
    private String oldPass;
    private String newPass;
    private String confPass;
}
