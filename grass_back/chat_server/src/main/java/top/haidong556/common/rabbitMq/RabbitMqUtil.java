package top.haidong556.common.rabbitMq;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RabbitMqUtil {
    final static private String QUEUE_NAME="test_queue";
    private static ConnectionFactory connectionFactory=null;
    private static Connection connection;
    static {
        Properties config=new Properties();
        connectionFactory=new ConnectionFactory();
        try {
            InputStream inputStream=  RabbitMqUtil.class.getClassLoader().getResourceAsStream("rabbitMq-config.properties");
            config.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        connectionFactory.setHost(config.getProperty("rabbit.host","localhost"));
        connectionFactory.setPort(Integer.parseInt(config.getProperty("rabbit.port","5672")));
        connectionFactory.setUsername(config.getProperty("rabbit.username","chat"));
        connectionFactory.setPassword(config.getProperty("rabbit.password","000227"));
        connectionFactory.setConnectionTimeout((Integer.parseInt(config.getProperty("rabbit.connectionTimeOut","1000000"))));
        connectionFactory.setVirtualHost(config.getProperty("rabbit.virtualHost","testhost"));
    }
    public static Connection getConnection() throws Exception {
        if(connection==null){
            synchronized (RabbitMqUtil.class){
                if(connection==null){
                    connection = connectionFactory.newConnection();
                }
            }
        }
        return connection;
    }

    public static Channel getChannel() throws Exception {
        return getConnection().createChannel();
    }

    public static boolean send(String msg,String queueName) throws Exception {
        // 获取到连接以及mq通道

        Channel channel = getChannel();

        // 声明（创建）队列
        channel.queueDeclare(queueName, false, false, false, null);


        channel.basicPublish("", queueName, null, msg.getBytes());

        channel.close();
        return true;
    }
}

