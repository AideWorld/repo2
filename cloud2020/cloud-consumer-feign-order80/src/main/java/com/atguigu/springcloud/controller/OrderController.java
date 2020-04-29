package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonReasult;
import com.atguigu.springcloud.feign.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhj
 */
@RestController
@Slf4j
public class OrderController {

    private final
    PaymentFeignService paymentFeignService;

    @Autowired
    public OrderController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonReasult getPaymentById(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }

}
