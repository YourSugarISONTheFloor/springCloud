package cn.fantuan.pay.controller;

import cn.fantuan.pay.service.PaymentService;
import cn.fantuan.springcloud.entities.CommonResult;
import cn.fantuan.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * @GetMapping，处理get请求
     * @PostMapping，处理post请求
     * @PutMapping，处理put请求
     * @DeleteMapping，处理delete请求
     */

    //@RequestMapping(method = RequestMethod.POST)的快捷方式。也就是可以简化成@PostMapping(value="/abc" )即可，主要是方便识记。
    //只接受post方式的请求
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        System.out.println(payment.toString());
        int result = paymentService.create(payment);
        log.info("********插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort： "+serverPort, result);
        } else {
            return new CommonResult(444, "插入失败,serverPort： "+serverPort, null);
        }
    }


    //只接受get方式的请求
    @GetMapping("/getPayment/{id}")
    //通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable("xxx")
    //http://localhost:8080/getPayment/12345
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPayment(id);
        log.info("********查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort： "+serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败，无该记录：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/timeout")
    public String paymentFeignTimeOut(){
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
