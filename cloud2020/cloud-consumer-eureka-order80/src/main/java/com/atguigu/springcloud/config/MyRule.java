package com.atguigu.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yhj
 */
@Configuration
public class MyRule {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }

}
