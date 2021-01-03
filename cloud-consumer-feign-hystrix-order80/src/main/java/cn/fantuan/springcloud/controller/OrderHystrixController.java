package cn.fantuan.springcloud.controller;

import cn.fantuan.springcloud.server.HystrixServer;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//定义全局的fallbackMethod
@DefaultProperties(defaultFallback = "globalFallBackMethod")
public class OrderHystrixController {

	@Autowired
	private HystrixServer hystrixServer;

	@GetMapping("/order/payment/hystrix/ok/{id}")
	public Object paymentInfo_ok(@PathVariable("id") Integer id) {
		return hystrixServer.paymentInfo_OK(id);
	}

	@GetMapping("/order/payment/hystrix/timeout/{id}")
	//指定特定的fallbackMethod
//	@HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod", commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//	})

	//使用全局的fallbackMethod
//	@HystrixCommand
	public Object paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//		int age = 10 / 0;
		return hystrixServer.paymentInfo_TimeOut(id);
	}

	public Object paymentTimeOutFallBackMethod(@PathVariable("id") Integer id) {
		return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
	}

	//全局fallbackMethod
	public String globalFallBackMethod() {
		return "全局fallbackMethod";
	}
}
