package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
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

    /** 保存商品 */
    ProductInfo save(ProductInfo productInfo);
}
