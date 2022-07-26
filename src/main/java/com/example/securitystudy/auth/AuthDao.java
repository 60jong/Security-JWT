package com.example.securitystudy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long insertRefreshToken(Long userid, String refreshToken) {
        String insertRefreshTokenQuery = "insert into token(user_id, refresh_token) values (?,?)";
        Object[] insertRefreshTokenParams = new Object[]{userid, refreshToken};

        this.jdbcTemplate.update(insertRefreshTokenQuery, insertRefreshTokenParams);

        return userid;
    }

    public String updateRefreshToken(Long userid, String newRefreshToken) {
        String updateRefreshTokenQuery = "update token set refresh_token = ? where user_id=?";
        Object[] updateRefreshTokenParams = new Object[]{newRefreshToken,userid};

        this.jdbcTemplate.update(updateRefreshTokenQuery, updateRefreshTokenParams);

        return newRefreshToken;
    }

    public boolean checkRefreshToken(String token) {
        String checkRefreshTokenQuery = "select exists(select refresh_token from token where refresh_token = ?)";

        int result = this.jdbcTemplate.queryForObject(checkRefreshTokenQuery,int.class,token);

        if (result != 1)
            return false;

        return true;
    }

    public boolean checkUser(Long userid) {
        String checkUserQuery = "select exists(select user_id from token where user_id=?)";

        int result = this.jdbcTemplate.queryForObject(checkUserQuery, int.class, userid);

        if (result != 1)
            return false;

        return true;
    }
}
