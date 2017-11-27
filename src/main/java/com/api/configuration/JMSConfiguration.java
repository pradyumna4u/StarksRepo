package com.api.configuration;

import javax.jms.JMSException;
import javax.jms.QueueConnectionFactory;

import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import com.api.framework.Prop;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class JMSConfiguration {

	private static String hostName = Prop.getProp("ibm.mq.hostname");
	private static String port = Prop.getProp("ibm.mq.port");
	private static String manager = Prop.getProp("ibm.mq.mgr");
	private static String channel = Prop.getProp("ibm.mq.channel");
	private static String username = Prop.getProp("ibm.mq.username");
	private static String password = Prop.getProp("ibm.mq.password");

	public static MQQueueConnectionFactory mqConnectionFactory() throws JMSException {
		MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
		mqQueueConnectionFactory.setHostName(hostName);
		mqQueueConnectionFactory.setPort(Integer.parseInt(port));
		mqQueueConnectionFactory.setQueueManager(manager);
		mqQueueConnectionFactory.setChannel(channel);
		mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
		return mqQueueConnectionFactory;
	}

	public static QueueConnectionFactory connectionFactory() throws JMSException {
		UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
		userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(mqConnectionFactory());
		userCredentialsConnectionFactoryAdapter.setUsername(username);
		userCredentialsConnectionFactoryAdapter.setPassword(password);
		userCredentialsConnectionFactoryAdapter.createQueueConnection();
		return userCredentialsConnectionFactoryAdapter;
	}

	public static JmsTemplate jmsTemplate() throws JMSException {
		return new JmsTemplate(JMSConfiguration.connectionFactory());
	}
}
