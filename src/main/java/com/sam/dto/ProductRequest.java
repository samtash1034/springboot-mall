package com.sam.dto;

import com.sam.constant.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String imgUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;

}
