package com.example.securitystudy;

import com.example.securitystudy.user.UserDao;
import com.example.securitystudy.user.UserProvider;
import com.example.securitystudy.user.UserService;
import com.example.securitystudy.util.BaseException;
import com.example.securitystudy.util.BaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserProviderTest {

    @Autowired
    UserProvider userProvider;

    @Test
    @Transactional
    @DisplayName("이메일로 유저 존재 테스트")
    void checkByEmail() throws BaseException {
        String email = "rudwhd515@gmail.com";

        System.out.println(userProvider.retrieveByEmail(email));
    }
}
