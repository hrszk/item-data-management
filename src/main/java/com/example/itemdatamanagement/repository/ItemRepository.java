package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.domain.ItemAndCategory;

@Repository
public class ItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<ItemAndCategory> ITEMANDCATEGORY_ROW_MAPPER = (rs, i) -> {
        ItemAndCategory itemAndCategory = new ItemAndCategory();
        itemAndCategory.setId(rs.getInt("i_id"));
        itemAndCategory.setName(rs.getString("i_name"));
        itemAndCategory.setCondition(rs.getInt("condition"));
        itemAndCategory.setCategory(rs.getInt("category"));
        itemAndCategory.setCategoryName(rs.getString("c_name"));
        itemAndCategory.setParentId(rs.getInt("parent_id"));
        itemAndCategory.setNameAll(rs.getString("name_all"));
        itemAndCategory.setBrand(rs.getString("brand"));
        itemAndCategory.setPrice(rs.getDouble("price"));
        itemAndCategory.setStock(rs.getInt("stock"));
        itemAndCategory.setShopping(rs.getInt("shopping"));
        itemAndCategory.setDescription(rs.getString("description"));
        return itemAndCategory;
    };

    // private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
    // Item item = new Item();
    // item.setId(rs.getInt("id"));
    // item.setName(rs.getString("name"));
    // item.setCondition(rs.getInt("condition"));
    // item.setCategory(rs.getInt("category"));
    // item.setBrand(rs.getString("brand"));
    // item.setPrice(rs.getDouble("price"));
    // item.setStock(rs.getInt("stock"));
    // item.setShopping(rs.getInt("shopping"));
    // item.setDescription(rs.getString("description"));
    // return item;
    // };

    // private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
    // Category category = new Category();
    // category.setId(rs.getInt("id"));
    // category.setName(rs.getString("name"));
    // category.setParentId(rs.getInt("parent_id"));
    // category.setNameAll(rs.getString("name_all"));
    // return category;
    // };

    public List<ItemAndCategory> findAll() {
        String findAllSql = """
                SELECT
                	i.id AS i_id,
                	i.name AS i_name,
                	i.condition,
                	i.category,
                	c.name AS c_name,
                	c.parent_id,
                	c.name_all,
                	i.brand,
                	i.price,
                	i.stock,
                	i.shopping,
                	i.description
                from items i
                INNER join category c ON i.category=c.id
                ORDER by i_id
                LIMIT 30;
                                """;
        ;
        List<ItemAndCategory> itemAndCategoryList = template.query(findAllSql,
                ITEMANDCATEGORY_ROW_MAPPER);
        return itemAndCategoryList;
    }

    // public List<Item> findAllItem() {
    // String findAllItemSql = "SELECT
    // id,name,condition,category,brand,price,stock,shopping,description FROM items
    // LIMIT 30;";
    // List<Item> itemList = template.query(findAllItemSql, ITEM_ROW_MAPPER);
    // return itemList;
    // }

    // public Category findByIdCategory(Integer id) {
    // String findByIdCategorySql = "SELECT id,name,parent_id,name_all FROM category
    // WHERE id=:id;";
    // SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    // Category category = template.queryForObject(findByIdCategorySql, param,
    // CATEGORY_ROW_MAPPER);
    // return category;
    // }

    public ItemAndCategory findById(Integer id) {
        String findByIdSql = """
                SELECT
                    i.id AS i_id,
                    i.name AS i_name,
                    i.condition,
                    i.category,
                    c.name AS c_name,
                    c.parent_id,
                    c.name_all,
                    i.brand,
                    i.price,
                    i.stock,
                    i.shopping,
                    i.description
                from items i
                INNER join category c ON i.category=c.id
                WHERE i.id=:id;
                    """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        ItemAndCategory itemAndCategory = template.queryForObject(findByIdSql, param, ITEMANDCATEGORY_ROW_MAPPER);
        return itemAndCategory;
    }
}
