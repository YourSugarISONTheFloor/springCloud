package cn.fantuan.mq.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {
    private static ConnectionFactory connectionFactory;

    //静态代码块在类加载的时候执行，只执行一次
    static {
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟机
        connectionFactory.setVirtualHost("/ems");

        //设置访问虚拟机的账号、密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
    }

    //定义提供链接对象的方法
    public static Connection getConnection() {
        try {
            //创建一个连接对象
            //Connection connection = connectionFactory.newConnection();
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接的工具方法
    public static void closeConnectionAndChanel(Channel channel, Connection connection) {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}