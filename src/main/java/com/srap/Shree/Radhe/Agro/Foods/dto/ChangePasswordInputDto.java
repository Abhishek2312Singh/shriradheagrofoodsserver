package com.srap.Shree.Radhe.Agro.Foods.dto;

import lombok.Data;

@Data
public class ChangePasswordInputDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
