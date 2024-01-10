package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.Category;

@Repository
public class CategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setParentId(rs.getInt("parent_id"));
        category.setNameAll(rs.getString("name_all"));
        return category;
    };

    public List<Category> findAllParentCategory() {
        String findAllParentCategorySql = """
                SELECT MIN(id) AS id, name, NULL AS parent_id, MIN(name_all) AS name_all
                FROM category
                WHERE parent_id IS NULL
                GROUP BY name;
                """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    public void deleteCategory(Integer id) {
        String sql = "DELETE FROM category WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

}
