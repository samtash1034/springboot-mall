package com.sam.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserLoginRequest {

    @NotBlank//不能為null&&不能為"   "和""(適合用在字串上)
    @Email
    private String email;

    @NotBlank
    private String password;
}
