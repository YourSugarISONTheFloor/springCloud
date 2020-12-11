package cn.fantuan.mq.rabbitmq.fanout;

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
        //参数二：交换机类型 fanout：广播类型
        channel.exchangeDeclare("logs", "fanout");

        //发送消息
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        //释放资源
        RabbitMQUtil.closeConnectionAndChanel(channel, connection);
    }
}
