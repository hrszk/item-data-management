package com.example.itemdatamanagement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.User;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insertUser(User user) {
        String insertUserSql = """
                insert into users(name,mail_address,password)
                VALUES(:name,:mailAddress,:password);
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        template.update(insertUserSql, param);
    }
}
