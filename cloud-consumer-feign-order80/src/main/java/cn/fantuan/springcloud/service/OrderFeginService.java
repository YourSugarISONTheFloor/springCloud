package cn.fantuan.springcloud.service;

import cn.fantuan.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface OrderFeginService {
    @GetMapping("/getPayment/{id}")
    CommonResult getPayment(@PathVariable("id") Long id);


    @GetMapping("/payment/lb")
    String getPaymentLB();

    //找CLOUD-PAYMENT-SERVICE服务名称下面的接口
    @GetMapping("/payment/timeout")
    String timeOut();
}
