package com.imooc.sell.controller;

import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtil;
import com.imooc.sell.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(
            @Valid OrderForm orderForm,
            BindingResult bindingResult)
    {
        //表单校验之后有没有错误
        if (bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //自己写一个封装好的转换类，避免重复代码
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("{[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        HashMap<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        ResultVO resultVO = ResultVOUtil.success(map);
        return resultVO;
    }

    //查看订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(
            @RequestParam("openid") String openid,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size
    )
    {
        if (StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOS = orderService.findList(openid, request);

//        ResultVO success = ResultVOUtil.success(orderDTOS.getContent());
        return ResultVOUtil.success(orderDTOS.getContent());
    }

    //查看订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(
            @RequestParam("openid") String openid,
            @RequestParam("orderId") String orderId
    ){
        //TODO 不安全的做法，之后还要改进
        //尽可能地把逻辑放在service层中去做，优化结构
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(
            @RequestParam("openid") String openid,
            @RequestParam("orderId") String orderId
    ){
        //TODO 不安全的做法，之后还要改进
        //尽可能地把逻辑放在service层中去做，优化结构
        buyerService.cancelOrderOne(openid, orderId);
        return ResultVOUtil.success();
    }

}
