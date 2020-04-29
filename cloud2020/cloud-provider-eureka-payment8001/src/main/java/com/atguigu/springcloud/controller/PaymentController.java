package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonReasult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author yhj
 */
@RestController
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment/create")
    public CommonReasult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);
        if (result > 0) {
            return new CommonReasult<>(200, "插入数据成功,serverPort：" + serverPort, result);
        } else {
            return new CommonReasult<>(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonReasult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("****查询结果：" + paymentById);
        if (paymentById != null) {
            return new CommonReasult<>(200, "查询成功,serverPort：" + serverPort, paymentById);
        } else {
            return new CommonReasult<>(444, "没有对应记录，查询id：" + id, null);
        }
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/lb")
    public String lb() {
        return serverPort;
    }

}
