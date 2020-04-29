package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhj
 */
@Mapper
public interface PaymentDao {

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
