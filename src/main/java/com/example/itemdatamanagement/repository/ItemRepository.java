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

@Repository
public class ItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

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
     * itemの論理削除
     * 
     * @param id 商品ID
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
     * itemの更新
     * 
     * @param item
     */
    public void updeteItem(Item item) {
        String sql = """
                UPDATE items SET name=:name,price=:price,category=:category,brand=:brand,condition=:condition,description=:description,update_time=NOW()
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
