package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @author yhj
 */
public interface PaymentService {

    /**
     * 插入
     * @param payment 传入Payment对象
     * @return sql语句生效的行数
     */
    int create(Payment payment);

    /**
     * 查询
     * @param id 传入id
     * @return 查询的结果
     */
    Payment getPaymentById(Long id);

}
