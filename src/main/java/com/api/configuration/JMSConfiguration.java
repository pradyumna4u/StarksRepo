package com.api.configuration;

import javax.jms.JMSException;
import javax.jms.QueueConnectionFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import com.api.framework.Prop;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class JMSConfiguration {

	private static String hostName = Prop.getProp("mosaic.mq.hostname");
	private static String port = Prop.getProp("mosaic.mq.port");
	private static String manager = Prop.getProp("mosaic.mq.mgr");
	private static String channel = Prop.getProp("mosaic.mq.channel");
	private static String username = Prop.getProp("mosaic.mq.username");
	private static String password = Prop.getProp("mosaic.mq.password");

	private static void setproperty(String queue) {

		hostName = Prop.getProp(queue.toLowerCase().trim() + ".mq.hostname");
		port = Prop.getProp(queue.toLowerCase().trim() + ".mq.port");
		manager = Prop.getProp(queue.toLowerCase().trim() + ".mq.mgr");
		channel = Prop.getProp(queue.toLowerCase().trim() + ".mq.channel");
		username = Prop.getProp(queue.toLowerCase().trim() + ".mq.username");
		password = Prop.getProp(queue.toLowerCase().trim() + ".mq.password");
	}

	private static MQQueueConnectionFactory mqConnectionFactory(String queue) throws JMSException {
		if (!StringUtils.isEmpty(queue)) {
			setproperty(queue);
		}
		MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
		mqQueueConnectionFactory.setHostName(hostName);
		mqQueueConnectionFactory.setPort(Integer.parseInt(port));
		mqQueueConnectionFactory.setQueueManager(manager);
		mqQueueConnectionFactory.setChannel(channel);
		mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
		return mqQueueConnectionFactory;
	}

	private static QueueConnectionFactory connectionFactory(String queue) throws JMSException {
		UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
		userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(mqConnectionFactory(queue));
		userCredentialsConnectionFactoryAdapter.setUsername(username);
		userCredentialsConnectionFactoryAdapter.setPassword(password);
		userCredentialsConnectionFactoryAdapter.createQueueConnection();
		return userCredentialsConnectionFactoryAdapter;
	}

	public static JmsTemplate jmsTemplate(String queue) throws JMSException {
		return new JmsTemplate(JMSConfiguration.connectionFactory(queue));
	}
}
