package com.sam.service.impl;

import com.sam.dao.ProductDao;
import com.sam.dto.ProductRequest;
import com.sam.model.Product;
import com.sam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);

    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {

        productDao.deleteProduct(productId);
    }
}
