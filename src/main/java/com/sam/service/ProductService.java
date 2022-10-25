package com.sam.service;

import com.sam.constant.ProductCategory;
import com.sam.dto.ProductRequest;
import com.sam.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);
}

