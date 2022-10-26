package com.sam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    @JsonProperty("e_mail")//返回給前端的json顯示不同名稱
    private String email;

    @JsonIgnore//不會將password回傳給前端
    //user object to json 時，會忽略password變數
    private String password;
    private Date createDate;
    private Date lastModifiedDate;
}
