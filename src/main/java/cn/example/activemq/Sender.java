package cn.example.activemq;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author
 * @company
 * @date
 */
public class Sender {
    static final String url = "tcp://139.199.15.166:61616";
    static final String username = "admin";
    static final String password = "350710135";
    private static final int SEND_NUM = 5;

    /**
     * 发送消息
     *
     * @param session
     * @param messageProducer
     */
    public static void sendMessage(Session session, MessageProducer messageProducer) {

        for (int i = 0; i < SEND_NUM; i++) {
            TextMessage message = null;
            try {
                //创建消息  
                message = session.createTextMessage("ActiveMQ发送消息:" + i);
                System.out.println("发送消息：" + "ActiveMQ发送消息" + i);
                messageProducer.send(message);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //ConnectionFactory 连接工厂，JMS用它建立工厂  
        ConnectionFactory connectionFactory;
        //Connection是JMS客户端连接Provider  
        Connection connection = null;
        //一个接收或者发送消息的线程  
        Session session;
        //消息的目的地  
        Destination destination;
        //消息发送者  
        MessageProducer messageProducer;
        //构造ConllectionFactory的实例对象，使用ActiveMQ  
        connectionFactory = new ActiveMQConnectionFactory(
                username,
                password,
                url);
        try {
            //构造connection  
            connection = connectionFactory.createConnection();
            connection.start();
            //获取连接  
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //通过链接创建队列给消息目的地  
            destination = session.createQueue("FirstQueue");
            //创建消息发送者  
            messageProducer = session.createProducer(destination);
            //设置不持久化  
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessage(session, messageProducer);
            session.commit();
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