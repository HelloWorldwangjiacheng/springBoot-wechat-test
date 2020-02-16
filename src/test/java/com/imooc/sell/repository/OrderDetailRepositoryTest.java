package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111111");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("https://avatars1.githubusercontent.com/u/40201433?v=4");
        orderDetail.setProductId("111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.3));
        orderDetail.setProductQuantity(2);

        OrderDetail save = repository.save(orderDetail);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails = repository.findByOrderId("123456");
        Assert.assertNotEquals(0,orderDetails.size());
    }
}