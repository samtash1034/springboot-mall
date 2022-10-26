package com.sam.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterRequest {

    /**
     *   NotNull//單純不能為null，可以為空
     *   NotBlank//不能為null&&不能為"   "和""(適合用在字串上)
     *   NotEmpty//只能加在集合上面 判斷size()有沒有>=1
     */

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
