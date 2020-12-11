package cn.fantuan.mq.rabbitmq.send;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        /*//创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟机的账号、密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");*/

        //获取连接对象
        Connection connection = RabbitMQUtil.getConnection();

        //获取连接中的通道
        Channel channel = connection.createChannel();

        //通道与消息队列绑定
        //参数一：队列的名称、在不存在的情况小自动创建
        //参数二：定义队列的特性是否要持久化，true代表持久化队列、false代表不持久化。。只能让队列持久化，不能让队列中消息持久化。重启服务器消息还是会丢失
        //参数三：是否独占队列，true代表独占队列、false代表不独占队列
        //参数四：是否在消费完成后自动删除队列，true代表自动删除、false代表不自动删除
        //参数五：额外参数。
        channel.queueDeclare("hello", false, false, false, null);

        //发布消息
        //参数一：交换机名称
        //参数二：队列名称
        //参数三：传递参数的额外设置，可以设置队列中消息的持久化
        //参数四：消息内容（字节类型）
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitsMQ".getBytes());

        /*//关闭连接
        channel.close();
        connection.close();*/
        RabbitMQUtil.closeConnectionAndChanel(channel,connection);
    }

}
