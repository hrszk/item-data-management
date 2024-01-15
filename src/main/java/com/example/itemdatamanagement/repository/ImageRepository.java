package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.Image;

@Repository
public class ImageRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Image> IMAGE_ROW_MAPPER = (rs, i) -> {
        Image image = new Image();
        image.setId(rs.getInt("id"));
        image.setItemId(rs.getInt("item_id"));
        image.setImagePath(rs.getString("image_path"));
        return image;
    };

    public void insertImage(Image image) {
        String sql = """
                INSERT INTO image(item_id,image_path)
                values(:itemId,:imagePath);
                    """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(image);
        template.update(sql, param);
    }

    public Image findByIdImage(Integer id) {
        String sql = """
                SELECT * FROM image WHERE item_id=:id;
                    """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        List<Image> images = template.query(sql, param, IMAGE_ROW_MAPPER);
        return images.isEmpty() ? null : images.get(0);
    }
}
