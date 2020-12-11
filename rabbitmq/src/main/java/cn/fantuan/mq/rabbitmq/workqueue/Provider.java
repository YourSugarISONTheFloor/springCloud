package cn.fantuan.mq.rabbitmq.workqueue;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();

        //通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);

        for (int i = 0; i < 10; i++) {
            //发布消息
            channel.basicPublish("", "work", null, (i + "hello work").getBytes());
        }

        //关闭资源
        RabbitMQUtil.closeConnectionAndChanel(channel, connection);
    }
}
