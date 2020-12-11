package cn.fantuan.pay.dao;

import cn.fantuan.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentDao {
    //增加
    int create(Payment payment);
    //查询
    Payment getPayment(@Param("id") Long id);
}
