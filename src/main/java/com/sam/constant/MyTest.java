package com.sam.constant;

public class MyTest {

    public static void main(String[] args) {

        ProductCategory category = ProductCategory.FOOD;
        String name = category.name();

        System.out.println(name);

        String s2 = "CAR";
        //去判斷字串有沒有對應的enum
        ProductCategory category2 = ProductCategory.valueOf(s2);
        System.out.println(category2);
    }
}
