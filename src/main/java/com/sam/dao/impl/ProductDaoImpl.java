package com.sam.dao.impl;

import com.sam.dao.ProductDao;
import com.sam.dto.ProductQueryParams;
import com.sam.dto.ProductRequest;
import com.sam.model.Product;
import com.sam.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        String sql = "SELECT product_id,product_name, category, image_url, " +
                "price, stock, description, created_date, last_modified_date " +
                "FROM product WHERE 1=1";
        //where 1=1對查詢沒有影響，主要是為了拼接

        Map<String, Object> map = new HashMap<>();

        if(productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category";//AND前面一定要有空格！！
            map.put("category", productQueryParams.getCategory().name());//enum 使用.name()
        }

        if(productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search";//% 不能寫在sql裡面(模糊查詢的%要寫在map裡面)
            map.put("search", "%" + productQueryParams.getSearch() + "%");//:對應的參數 後面對應要傳入的參數
        }

        //order by只能使用字串的拼接（已經有給預設值所以不會是null)
        //ORDER BY 前後要加上空格！！
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {

        String sql = "select product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product(product_name, category, image_url, price, " +
                "stock, description, created_date, last_modified_date) values(:productName, :category, :imgUrl, :price, " +
                ":stock,:description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        System.out.println("1:" + productRequest.getCategory());//CAR(productCategory)
        map.put("category", productRequest.getCategory().toString());//必須轉成string存到資料庫才不會出現問題
        map.put("imgUrl", productRequest.getImgUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        //取得自動生成的key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        String sql = "update product set product_name = :productName, " +
                "category = :category, image_url = :imgUrl, price = :price, " +
                "stock = :stock, description = :description, " +
                "last_modified_date = :lastModifiedDate where " +
                "product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imgUrl", productRequest.getImgUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {

        String sql = "delete from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
        //delete 和 select 都要寫from

    }

}
