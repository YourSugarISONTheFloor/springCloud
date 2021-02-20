package cn.fan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ReceiverMessageListerController {
	@Value("${server.port}")
	private String serverPort;

	@StreamListener(Sink.INPUT)
	public void input(Message<String> message) {
		System.err.println("我是消费者1号");
		System.out.println(message.getPayload());
		System.out.println("端口号：" + serverPort);
	}

}
