package com.api.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.QueueConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@Configuration
public class JMSConfiguration {

    @Value("${ibm.mq.hostname}")
    private String hostName;

    @Value("${ibm.mq.port}")
    private int port;

    @Value("${ibm.mq.mgr}")
    private String manager;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.username}")
    private String username;

    @Value("${ibm.mq.password}")
    private String password;

    @Bean
    public MQQueueConnectionFactory mqConnectionFactory() throws JMSException {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(hostName);
        mqQueueConnectionFactory.setPort(port);
        mqQueueConnectionFactory.setQueueManager(manager);
        mqQueueConnectionFactory.setChannel(channel);
        mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        return mqQueueConnectionFactory;
    }

    @Bean
    public QueueConnectionFactory connectionFactory() throws JMSException {
        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        userCredentialsConnectionFactoryAdapter
                .setTargetConnectionFactory(mqConnectionFactory());
        userCredentialsConnectionFactoryAdapter.setUsername(username);
        userCredentialsConnectionFactoryAdapter.setPassword(password);
        userCredentialsConnectionFactoryAdapter.createQueueConnection();
        return userCredentialsConnectionFactoryAdapter;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }
}
