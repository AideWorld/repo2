package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yhj
 */
@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK,id:" + id + " 正常访问！";
    }

    @HystrixCommand(
            fallbackMethod = "paymentInfoTimeoutHandler",
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            })
    public String paymentInfoTimeout(Integer id) {
        int timeNumber = 3000;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK,id:" + id + " 耗时(秒):" + "(*^_^*)";
    }

    private String paymentInfoTimeoutHandler(Integer id) {
        return "当前线程池名字" + Thread.currentThread().getName() + "调用支付接口超时或异常，/(ㄒoㄒ)/~~";
    }

    /**
     * 测试熔断
     */

    private String defaultPaymentInfoTimeoutHandler(@PathVariable Long id) {
        return "我是测试熔断的兜底方法" + id;
    }

    @HystrixCommand(
            fallbackMethod = "defaultPaymentInfoTimeoutHandler",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
            }
    )
    public String paymentCircuitBreaker(@PathVariable Long id) {
        if (id < 0) {
            throw new RuntimeException("****id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

}
