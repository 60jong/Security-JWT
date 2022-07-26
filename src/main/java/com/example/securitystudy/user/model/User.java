package com.example.securitystudy.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String introduce;
    private String role;
    private String provider;
    private String provider_id;

    public User(String name, String nickname, String email, String password, String role, String provider, String provider_id){
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.provider_id = provider_id;
    }

    public User(Long id ,String name, String nickname, String email, String password, String role, String provider, String provider_id){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.provider_id = provider_id;
    }
}
