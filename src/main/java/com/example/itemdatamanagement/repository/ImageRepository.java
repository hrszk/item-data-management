package com.example.itemdatamanagement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.Image;

@Repository
public class ImageRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insertImage(Image image) {
        String sql = """
                INSERT INTO image(item_id,image_path)
                values(:itemId,:imagePath);
                    """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(image);
        template.update(sql, param);
    }
}
