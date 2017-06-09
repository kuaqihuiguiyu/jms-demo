package com.hm;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.*;
import javax.transaction.SystemException;

/**
 * 点对点模式
 * Created by hm on 2017/6/8.
 */
@SpringBootApplication
@EnableJms
public class PtpApplication {
    @Value("${jms.broker-url}")
    private String jmsBrokerUrl;
    @Value("${jms.user}")
    private String jmsUser;
    @Value("${jms.password}")
    private String jmsPassword;


/**
 * 创建 ActiveMQ 的连接工厂
 */

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsBrokerUrl);
        connectionFactory.setUserName(jmsUser);
        connectionFactory.setPassword(jmsPassword);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsQueueTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    public JmsTemplate jmsTopicTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
/*
    *//**
     * 分布式事务
     *//*
    @Bean
    public JtaTransactionManager jmsTransactionManager() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return new JtaTransactionManager(userTransactionImp, userTransactionManager);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(PtpApplication.class, args);
    }
}
