package cn.fantuan.mq.rabbitmq.workqueue;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer1 {
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
        //参数一：消费那个队列的消息，队列名称
        //参数二：开启消息的自动确认机制，true 消费者自动向RabbitMQ确认消息消费
        //参数三：消费消息时的回调接口
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
            }
        });
    }
}
