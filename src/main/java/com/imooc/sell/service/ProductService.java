package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 用查询页码的方式，查询商品信息
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品,也就是写商品进入数据库
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOS);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOS);
}
