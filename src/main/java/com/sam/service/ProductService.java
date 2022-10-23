package com.sam.service;

import com.sam.model.Product;
import org.springframework.stereotype.Service;

public interface ProductService {

    Product getProductById(Integer productId);
}
