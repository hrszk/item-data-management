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
                SELECT * FROM category WHERE parent_id IS NULL AND name_all IS NULL;
                    """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    public List<Category> findAllChildCategory() {
        String findAllParentCategorySql = """
                SELECT * FROM category WHERE parent_id=1 AND name_all IS NULL;
                    """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    public List<Category> findAllGrandChild() {
        String findAllParentCategorySql = """
                SELECT  MIN(id) AS id,name,2 AS parent_id,MIN(name_all) AS name_all
                FROM category
                WHERE parent_id=2 AND name_all IS NOT NULL
                GROUP by name
                order by name;
                        """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    public void deleteCategory(Integer id) {
        String sql = "DELETE FROM category WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public List<Category> findByNameCategory(String nameAll) {
        String sql = """
                SELECT * FROM category WHERE name_all=:nameAll
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll + "%");
        List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
        return categoryList;

    }

}
