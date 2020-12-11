package cn.fantuan.mq.rabbitmq.workqueue;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer2 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通过通道绑定队列
        channel.queueDeclare("work", true, false, false, null);

        //告诉通道每一次只能消费一个消息
        channel.basicQos(1);

        //消费消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
                //手动确认
                //参数一：手动确认消息标识，确认队列中那个具体的消息
                //参数二：是否开启多个消息同时确认
                //envelope.getDeliveryTag() 当前消息在队列中的标志
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
