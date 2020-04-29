package com.atguigu.springcloud;

import com.atguigu.springcloud.config.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author yhj
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-provider-service", configuration = MyRule.class)
public class OrderEurekaMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderEurekaMain80.class, args);
    }
}
