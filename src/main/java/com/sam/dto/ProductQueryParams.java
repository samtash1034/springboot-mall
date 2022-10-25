package com.sam.dto;

import com.sam.constant.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryParams {

    private ProductCategory category;

    private String search;

    private String orderBy;

    private String sort;

    private Integer limit;

    private Integer offset;
}
