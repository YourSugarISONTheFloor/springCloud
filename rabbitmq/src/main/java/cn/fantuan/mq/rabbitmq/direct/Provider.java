package cn.fantuan.mq.rabbitmq.direct;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //将通道声明指定的交换机
        //参数一：交换机名称
        //参数二：交换机类型 direct：路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        //定义路由key
        String routKey="info";
        //发送消息
        channel.basicPublish("logs", routKey, null, ("这是direct模型的发布的基于route key：["+routKey+"] 发送的消息").getBytes());
        //释放资源
        RabbitMQUtil.closeConnectionAndChanel(channel, connection);
    }
}
