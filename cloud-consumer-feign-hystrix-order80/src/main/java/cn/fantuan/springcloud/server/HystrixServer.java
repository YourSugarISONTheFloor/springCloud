package cn.fantuan.springcloud.server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface HystrixServer {
	@GetMapping(value = "/hystrix/ok/{id}")
	String paymentInfo_OK(@PathVariable("id") Integer id);

	@GetMapping("/hystrix/timeout/{id}")
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
	String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
