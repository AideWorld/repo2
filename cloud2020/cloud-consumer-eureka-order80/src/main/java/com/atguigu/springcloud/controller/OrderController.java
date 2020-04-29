package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonReasult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author yhj
 */
@RestController
@Slf4j
public class OrderController {

    private final RestTemplate restTemplate;

    private String paymentUrl = "http://cloud-payment-service";

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/create")
    public CommonReasult create(Payment payment) {
        return restTemplate.postForObject(paymentUrl + "/payment/create", payment, CommonReasult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonReasult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(paymentUrl + "/payment/get/" + id, CommonReasult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonReasult get(@PathVariable("id") Long id) {
        ResponseEntity<CommonReasult> entity = restTemplate.getForEntity(paymentUrl + "/payment/get/" + id, CommonReasult.class);
        log.info(entity.getStatusCode()+""+entity.getStatusCodeValue()+entity.getHeaders());
        return entity.getStatusCode().is2xxSuccessful() ? entity.getBody() : new CommonReasult(444, "操作失败");
    }

}
