package cn.fantuan.mq.rabbitmq.topic;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer1 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();

        //定义交换机的名称
        String exchangeName="topics";

        //通道绑定交换机，名字和类型
        channel.exchangeDeclare(exchangeName, "topic");

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列。 队列、交换机名字和路由key
        channel.queueBind(queueName, exchangeName, "user.*");

        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("user.*的路由key消费者：" + new String(body));
            }
        });
    }
}
