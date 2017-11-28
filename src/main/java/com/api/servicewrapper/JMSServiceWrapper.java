package com.api.servicewrapper;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import com.api.configuration.JMSConfiguration;

public class JMSServiceWrapper {

	public static String receiveMessage(String INPUTQUEUE) throws JMSException {
		TextMessage textMessage = (TextMessage) JMSConfiguration.jmsTemplate().receive(INPUTQUEUE);
		return textMessage.getText();
	}

	public static List<TextMessage> receiveAllFromQueue(String destination) throws JmsException, JMSException {
		List<TextMessage> messages = new ArrayList<TextMessage>();
		TextMessage TextMessage;
		JmsTemplate template = JMSConfiguration.jmsTemplate();
		template.setReceiveTimeout(2000);
		while ((TextMessage = (TextMessage) template.receive(destination)) != null) {
			System.out.println("#####################"+TextMessage.getText());
			messages.add(TextMessage);
		}
		return messages;
	}

	public static void sendMessage(final String message, String QUEUENAME) throws JmsException, JMSException {
		JMSConfiguration.jmsTemplate().send(QUEUENAME, session -> {
			TextMessage textMessage = session.createTextMessage(message);
			textMessage.setJMSCorrelationID("JMS_CORRELATION_ID");
			textMessage.setJMSMessageID("JMS_MESSAGE_ID");
			textMessage.setStringProperty("name", "Jag");
			return textMessage;
		});
	}

}
