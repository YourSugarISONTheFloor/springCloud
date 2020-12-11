package cn.fantuan.mq.rabbitmq.send;

import cn.fantuan.mq.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    //消费消息
    public static void main(String[] args) throws IOException, TimeoutException {

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


        //创建一个连接对象
        Connection connection = RabbitMQUtil.getConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("hello", false, false, false, null);

        //消费消息
        //参数一：消费那个队列的消息，队列名称
        //参数二：开启消息的自动确认机制
        //参数三：消费消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            /**
             *
             * @param consumerTag   标签
             * @param envelope      消息传递过程中的整个信封
             * @param properties    消息传递的一些属性
             * @param body          消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });

        /*//关闭连接，不关闭的话会一直监听队列中的消息，一般都不关闭连接。
        channel.close();
        connection.close();*/
        RabbitMQUtil.closeConnectionAndChanel(channel, connection);

    }
}
