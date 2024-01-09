package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.User;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setMailAddress(rs.getString("mail_address"));
        user.setPassword(rs.getString("password"));
        user.setAuthority(rs.getInt("authority"));
        return user;
    };

    public void insertUser(User user) {
        String insertUserSql = """
                insert into users(name,mail_address,password)
                VALUES(:name,:mailAddress,:password);
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        template.update(insertUserSql, param);
    }

    public User findByMailAddressAndPassword(String mailAddress, String password) {
        String findByMailAddressAndPasswordSql = """
                SELECT
                    id,
                    name,
                    mail_address,
                    password,
                    authority
                FROM users
                WHERE mail_address=:mailAddress AND password=:password;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
                password);
        List<User> userList = template.query(findByMailAddressAndPasswordSql, param, USER_ROW_MAPPER);

        return userList. isEmpty() ? null : userList.get(0);
        
    }
}
