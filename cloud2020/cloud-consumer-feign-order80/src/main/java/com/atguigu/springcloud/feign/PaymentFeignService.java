package com.atguigu.springcloud.feign;

import com.atguigu.springcloud.dto.CommonReasult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yhj
 */
@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    /**
     * 通过id获取
     * @param id 参数id，payment表的主键
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonReasult getPaymentById(@PathVariable("id") Long id);

    /**
     * 超时测试
     * @return 端口
     */
    @GetMapping(value = "/payment/feign/timeout")
    String timeout();

}
