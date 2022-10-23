package com.sam.service;

import com.sam.dto.ProductRequest;
import com.sam.model.Product;
import org.springframework.stereotype.Service;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}

