package cn.example.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
/**
 * @author
 * @company
 * @date
 */
public class Receiver {
    static final String url = "tcp://139.199.15.166:61616";
    static final String username = "admin";
    static final String password = "350710135";

    public static void main(String[] args) {
        //ConnectionFactory连接工厂  
        ConnectionFactory connectionFactory;
        //是JMS客户端连接JMS Provider:他是用来处理消息路由与传递的消息  
        Connection connection = null;
        //创建连接  
        Session session;
        //消息目的地  
        Destination destination;
        //消息接收者  
        MessageConsumer messageConsumer;
        connectionFactory = new ActiveMQConnectionFactory(username, password, url);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            messageConsumer = session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(500000);
                if (null != textMessage) {
                    System.out.println("收到消息:" + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}  