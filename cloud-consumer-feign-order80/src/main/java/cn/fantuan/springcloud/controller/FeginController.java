package cn.fantuan.springcloud.controller;


import cn.fantuan.springcloud.entities.CommonResult;
import cn.fantuan.springcloud.entities.Payment;
import cn.fantuan.springcloud.service.OrderFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FeginController {
    @Autowired
    private OrderFeginService orderFeginService;

    @GetMapping("/fegin/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return orderFeginService.getPayment(id);
    }


    @GetMapping("/feign/lb")
    public String paymentLB(){
        return orderFeginService.getPaymentLB();
    }

    @GetMapping("/feign/timeout")
    public String feignTimeOut(){
        return orderFeginService.timeOut();
    }
}
