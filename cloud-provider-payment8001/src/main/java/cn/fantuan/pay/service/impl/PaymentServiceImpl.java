package cn.fantuan.pay.service.impl;


import cn.fantuan.pay.dao.PaymentDao;
import cn.fantuan.springcloud.entities.Payment;
import cn.fantuan.pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {
    //自动注入
    @Autowired
    private PaymentDao paymentDao;

   public int create(Payment payment){
       return paymentDao.create(payment);
   }
   public Payment getPayment(Long id){
       return paymentDao.getPayment(id);
   }
}