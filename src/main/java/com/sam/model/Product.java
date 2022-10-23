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
    //預設返回GMT+0
    private Date createdDate;
    private Date lastModifiedDate;

    //timestamp(時間戳）
    //記錄從英國1970 1/1 凌晨 00:00 到現在的總秒數
    //全世界的時間不一樣，但timestamp一定一樣

}
