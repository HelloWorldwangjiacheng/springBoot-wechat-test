package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)      //当有字段是null时，就不显示在前端，但是如果所有对象都写，那样太烦，直接写在配置文件中
public class OrderDTO {
    /** 订单ID */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态 默认为新下单 */
    private Integer orderStatus;

    /** 支付状态 默认为0未支付 */
    private Integer payStatus;

    /** 订单创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 订单更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList = new ArrayList<>();
}
