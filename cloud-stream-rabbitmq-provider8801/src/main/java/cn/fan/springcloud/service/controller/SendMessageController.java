package cn.fan.springcloud.service.controller;

import cn.fan.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

	@Autowired
	private IMessageProvider iMessageProvider;

	@GetMapping("/send")
	public String sendMessage() {
		return iMessageProvider.send();
	}
}
