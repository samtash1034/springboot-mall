package com.sam.controller;

import com.sam.constant.ProductCategory;
import com.sam.dto.ProductRequest;
import com.sam.model.Product;
import com.sam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,//category可傳可不傳(required=false)
            @RequestParam(required = false) String search
    ){

        List<Product> productList = productService.getProducts(category, search);

        //productList沒有判斷(列表類型api不管有沒有查到數據都要回200)
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        //查詢單個都要去判斷，如果查不會則回傳404
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        Product productById = productService.getProductById(productId);

        if(productById == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){

//        Product productById = productService.getProductById(productId);
//        if(productById == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        //delete不需要去判斷id是否存在，不存在也要回傳204，因為商品已經不存在了

        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
