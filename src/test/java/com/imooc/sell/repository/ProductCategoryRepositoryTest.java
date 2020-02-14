package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
//        Optional<ProductCategory> productCategory = repository.findById(1);
//        System.out.println(productCategory.get().getCategoryId());
//        System.out.println(productCategory.get().getCategoryName());
//
        ProductCategory product = new ProductCategory();
        product.setCategoryId(1);
        Example<ProductCategory> example = Example.of(product);
        Optional<ProductCategory> productCategory1 = repository.findOne(example);
        ProductCategory result = productCategory1.get();
        System.out.println(result.getCategoryName());
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱",3);
//        productCategory.setCategoryId(2);
//        productCategory.setCategoryName("男生最爱");
//        productCategory.setCategoryType(3);
        ProductCategory save = repository.save(productCategory);
        Assert.assertNotNull(save);
//        Assert.assertNotEquals(null,save);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3);
        List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }
}