package cn.fantuan.springcloud.server;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements HystrixServer {
	@Override
	public String paymentInfo_OK(Integer id) {
		return "--- PaymentFallbackService fall back paymentInfo_OK";
	}

	@Override
	public String paymentInfo_TimeOut(Integer id) {
		return "--- PaymentFallbackService fall back paymentInfo_TimeOut";
	}
}
