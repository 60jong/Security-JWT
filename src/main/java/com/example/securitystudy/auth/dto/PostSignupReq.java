package com.example.securitystudy.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignupReq {
    private String username;
    private String nickname;
    private String email;
    private String password;
}
