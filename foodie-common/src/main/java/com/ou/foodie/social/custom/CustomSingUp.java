package com.ou.foodie.social.custom;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomSingUp implements ConnectionSignUp {
    private  JdbcTemplate jdbcTemplate;
    @Override
    public String execute(Connection<?> connection) {
        String displayName = connection.getDisplayName();
        String imageUrl = connection.getImageUrl();
        String UserId="3";

        String sql="insert into userconnection (userId,displayName,imageUrl) values (?,?,?)";
        jdbcTemplate.update(sql,UserId,displayName,imageUrl);
        return UserId;
    }
}
