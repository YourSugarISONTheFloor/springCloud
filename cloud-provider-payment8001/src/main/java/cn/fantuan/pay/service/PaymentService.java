package cn.fantuan.pay.service;

import cn.fantuan.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {
    //增加
    int create(Payment payment);
    //查询
    Payment getPayment(@Param("id") Long id);
}