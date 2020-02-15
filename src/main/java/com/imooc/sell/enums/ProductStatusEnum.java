package com.imooc.sell.enums;

import lombok.Getter;

/**
 * 商品状态，即商品是否在架
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"任在架"),
    DOWN(1,"已下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
