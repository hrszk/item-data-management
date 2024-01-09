package com.example.itemdatamanagement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.Original;

@Repository
public class OriginalRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = (rs, i) -> {
        Original original = new Original();
        original.setTrainId(rs.getInt("train_id"));
        original.setName(rs.getString("name"));
        original.setItemConditionId(rs.getInt("item_condition_id"));
        original.setCategoryName(rs.getString("category_name"));
        original.setBrandName(rs.getString("brand_name"));
        original.setPrice(rs.getDouble("price"));
        original.setShopping(rs.getInt("shopping"));
        original.setItemDescription(rs.getString("item_description"));
        return original;
    };

    public void insertOriginal(Original original) {
        String insertOriginalSql = """
                INSERT INTO original(name,item_condition_id,category_name,brand_name,price,shopping,item_description)
                VALUES(:name,:itemConditionId,:categoryName,:brandName,:price,:shopping,itemDescription);
                    """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(original);
        template.update(insertOriginalSql, param);
    }

}
