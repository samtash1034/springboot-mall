package com.sam.model;

import com.sam.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private Integer productId;
    private String productName;
    private ProductCategory category;
    private String imgUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;

}
