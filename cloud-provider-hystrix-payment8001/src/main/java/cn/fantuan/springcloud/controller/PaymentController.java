package cn.fantuan.springcloud.controller;

import cn.fantuan.springcloud.server.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/hystrix/ok/{id}")
	public Object paymentInfo_OK(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfo_OK(id);
		log.info("ok*********:" + result);
		return result;
	}

	@GetMapping("/hystrix/timeout/{id}")
	public Object paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfo_TimeOut(id);
		log.info("timeout********:" + result);
		return result;
	}

	//============服务熔断
	@GetMapping("/hystrix/circuit/{id}")
	public Object paymentCircuit(@PathVariable("id") Integer id) {
		String s = paymentService.paymentCircuitBreaker(id);
		log.info("paymentCircuit:  " + s);
		return s;

	}
}