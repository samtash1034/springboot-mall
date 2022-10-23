package com.sam.dao;

import com.sam.dto.ProductRequest;
import com.sam.model.Product;

public interface ProductDao {

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}

