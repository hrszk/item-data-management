package com.example.itemdatamanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.itemdatamanagement.domain.ItemAndCategory;

@Repository
public class ItemAndCategoryRepository {

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
        itemAndCategory.setShipping(rs.getInt("shipping"));
        itemAndCategory.setDescription(rs.getString("description"));
        return itemAndCategory;
    };

    public List<ItemAndCategory> searchItem(String name, String nameAll, String brand) {
        String sql = """
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
                    i.shipping,
                    i.description
                from items i
                INNER join category c ON i.category=c.id
                WHERE i.name LIKE :name AND c.name_all LIKE :nameAll AND i.brand LIKE :brand;
                        """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
                .addValue("nameAll", nameAll + "%").addValue("brand", "%" + brand + "%");
        List<ItemAndCategory> itemAndCategoryList = template.query(sql, param, ITEMANDCATEGORY_ROW_MAPPER);
        return itemAndCategoryList;
    }
}
