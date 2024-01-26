package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
        itemAndCategory.setParent(rs.getInt("parent"));
        itemAndCategory.setNameAll(rs.getString("name_all"));
        itemAndCategory.setBrand(rs.getString("brand"));
        itemAndCategory.setPrice(rs.getDouble("price"));
        itemAndCategory.setShipping(rs.getInt("shipping"));
        itemAndCategory.setDescription(rs.getString("description"));
        itemAndCategory.setUpdateTime(rs.getTimestamp("update_time"));
        itemAndCategory.setDelFlg(rs.getInt("del_flg"));
        return itemAndCategory;
    };

    private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setCondition(rs.getInt("condition"));
        item.setCategory(rs.getInt("category"));
        item.setBrand(rs.getString("brand"));
        item.setPrice(rs.getDouble("price"));
        item.setShipping(rs.getInt("shipping"));
        item.setDescription(rs.getString("description"));
        item.setUpdateTime(rs.getTimestamp("update_time"));
        item.setDelFlg(rs.getInt("del_flg"));
        return item;
    };

    /**
     * itemとcategory全件検索
     * 
     * @return itemとcategory全件
     */
    public List<ItemAndCategory> findAll() {
        String findAllSql = """
                SELECT
                    i.id AS i_id,
                    i.name AS i_name,
                    i.condition,
                    i.category,
                    c.name AS c_name,
                    c.parent,
                    c.name_all,
                    i.brand,
                    i.price,
                    i.shipping,
                    i.description,
                    i.update_time,
                    i.del_flg
                from items i
                INNER join category c ON i.category=c.id
                WHERE i.shipping=0
                ORDER by c.name_all
                LIMIT 600;
                """;
        List<ItemAndCategory> itemAndCategoryList = template.query(findAllSql,
                ITEMANDCATEGORY_ROW_MAPPER);
        return itemAndCategoryList;
    }

    /**
     * 名前からitemとcategory検索
     * 
     * @param name
     * @return 対象のitemとcategory全件
     */
    // public List<ItemAndCategory> findByName(String name) {
    // String findByNameSql = """
    // SELECT
    // i.id AS i_id,
    // i.name AS i_name,
    // i.condition,
    // i.category,
    // c.name AS c_name,
    // c.parent,
    // c.name_all,
    // i.brand,
    // i.price,
    // i.shipping,
    // i.description,
    // i.update_time,
    // i.del_flg
    // from items i
    // INNER join category c ON i.category=c.id
    // WHERE i.name LIKE ':name';
    // ORDER by c.name_all
    // """;
    // SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" +
    // name + "%");
    // List<ItemAndCategory> itemAndCategoryList = template.query(findByNameSql,
    // param, ITEMANDCATEGORY_ROW_MAPPER);
    // return itemAndCategoryList;
    // }

    /**
     * idでitemとcategoryの検索
     * 
     * @param id
     * @return itemとcategory
     */
    public ItemAndCategory findById(Integer id) {
        String findByIdSql = """
                SELECT
                    i.id AS i_id,
                    i.name AS i_name,
                    i.condition,
                    i.category,
                    c.name AS c_name,
                    c.parent,
                    c.name_all,
                    i.brand,
                    i.price,
                    i.shipping,
                    i.description,
                    i.update_time,
                    i.del_flg
                from items i
                INNER join category c ON i.category=c.id
                WHERE i.id=:id
                    """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        ItemAndCategory itemAndCategory = template.queryForObject(findByIdSql, param, ITEMANDCATEGORY_ROW_MAPPER);
        return itemAndCategory;
    }

    /**
     * itemの削除
     * 
     * @param id
     */
    public void deleteItem(Integer id) {
        String deleteItemSql = "UPDATE items SET del_flg=1 WHERE id=:id;";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(deleteItemSql, param);
    }

    /**
     * idでitemを検索
     * 
     * @param id
     * @return item
     */
    public Item findByIdItem(Integer id) {
        String sql = "SELECT * FROM items WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
        return item;
    }

    /**
     * itemデータの更新
     * 
     * @param item
     */
    public void updeteItem(Item item) {
        String sql = """
                UPDATE items SET name=:name,price=:price,category=:category,brand=:brand,condition=:condition,description=:description
                WHERE id=:id;
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(item);
        template.update(sql, param);
    }

    public void insertItem(Item item) {
        String sql = """
                INSERT INTO items(name,condition,category,brand,price,shipping,description,update_time,del_flg)
                values(:name,:condition,:category,:brand,:price,:shipping,:description,NOW(),:delFlg);
                    """;

        SqlParameterSource param = new BeanPropertySqlParameterSource(item);
        template.update(sql, param);
    }

    public Item findByNewItem() {
        String sql = """
                SELECT * FROM items ORDER by id DESC LIMIT 1;
                    """;

        List<Item> items = template.query(sql, ITEM_ROW_MAPPER);
        return items.isEmpty() ? null : items.get(0);
    }
}
