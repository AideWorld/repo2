package com.atguigu.springcloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author yhj
 */
@Component
public class DefaultPaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "我是兜底的OkOkOk";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "我是兜底的Timeout";
    }
}
