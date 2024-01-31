package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
        category.setParent(rs.getInt("parent"));
        category.setName(rs.getString("name"));
        category.setNameAll(rs.getString("name_all"));
        return category;
    };

    /**
     * 親カテゴリ全件検索
     * 
     * @return 親カテゴリ一覧
     */
    public List<Category> findAllParentCategory() {
        String findAllParentCategorySql = """
                SELECT * FROM category WHERE parent IS NULL AND name IS NOT NULL
                ORDER BY name;
                    """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    /**
     * 子カテゴリ全件検索
     * 
     * @return 子カテゴリ一覧
     */
    public List<Category> findAllChildCategory() {
        String findAllParentCategorySql = """
                SELECT  MIN(id) AS id,name,1 AS parent,MIN(name_all) AS name_all
                FROM category
                WHERE parent=1
                GROUP by name
                order by name;
                        """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    /**
     * 孫カテゴリ全件検索
     * 
     * @return 孫カテゴリ一覧
     */
    public List<Category> findAllGrandChild() {
        String findAllParentCategorySql = """
                SELECT  MIN(id) AS id,name,2 AS parent,MIN(name_all) AS name_all
                FROM category
                WHERE parent=2 AND name_all IS NOT NULL
                GROUP by name
                order by name;
                        """;

        List<Category> parentCategoryList = template.query(findAllParentCategorySql, CATEGORY_ROW_MAPPER);
        return parentCategoryList;
    }

    /**
     * カテゴリ削除
     * 
     * @param id
     */
    public void deleteCategory(Integer id) {
        String sql = "DELETE FROM category WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public void deleteCategoryByNameAll(String nameAll) {
        String sql = "DELETE FROM category WHERE name_all=:nameAll;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll);
        template.update(sql, param);
    }

    /**
     * nameAllで検索
     * 
     * @param nameAll
     * @return 検索結果の1件目または、検索結果がない場合はnull
     */
    public Category findByNameCategory(String nameAll) {
        String sql = """
                SELECT * FROM category WHERE name_all=:nameAll;
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll);
        List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
        return categoryList.isEmpty() ? null : categoryList.get(0);
    }

    /**
     * nameAllで孫カテゴリ検索
     * 
     * @param nameAll
     * @return 検索結果の1件目または、検索結果がない場合はnull
     */
    public Category findByNameAllGrandChild(String nameAll) {
        String sql = """
                SELECT * FROM category WHERE name_all LIKE :nameAll AND parent=2;
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll + "%");
        List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
        return categoryList.isEmpty() ? null : categoryList.get(0);
    }

    public Category findByIdCategory(Integer id) {
        String sql = """
                SELECT * FROM category WHERE id=:id;
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
        return category;
    }

    public List<Category> findByParentCategory(String parentCategory) {
        String sql = """
                SELECT  MIN(id) AS id,name,2 AS parent,MIN(name_all) AS name_all
                FROM category
                WHERE parent=2 AND name_all LIKE :parentCategory
                GROUP by name
                order by name;
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("parentCategory", parentCategory + "%");
        List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
        return categoryList;
    }

    public void insertParentCategory(String name) {
        String Sql = """
                INSERT INTO category(name) VALUES(:name);
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
        template.update(Sql, param);
    }

    public void insertCategory(Category category) {
        String Sql = """
                INSERT INTO category(name,parent,name_all) VALUES(:name,:parent,:nameAll);
                """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(category);
        template.update(Sql, param);
    }

    /**
     * 中カテゴリ、小カテゴリの編集
     * 
     * @param name    カテゴリ名
     * @param nameAll カテゴリ名全文
     * @param id      カテゴリID
     */
    public void updateChildCategoryAndGrandChild(String name, String nameAll, Integer id) {
        String sql = """
                UPDATE category SET name=:name,name_All=:nameAll WHERE id=:id;
                    """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("nameAll", nameAll)
                .addValue("id", id);
        template.update(sql, param);
    }

}
