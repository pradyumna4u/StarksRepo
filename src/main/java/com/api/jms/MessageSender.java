package com.api.jms;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${ibm.mq.inputQueueName}")
    private String inputQueue;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final String message) {
        log.info("Sending the message to input queue: {} with payload:\n{}",
                inputQueue, message);

        jmsTemplate.send(inputQueue, session -> {
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setJMSCorrelationID("JMS_CORRELATION_ID");
            textMessage.setJMSMessageID("JMS_MESSAGE_ID");
            textMessage.setStringProperty("name", "Jag");
            return textMessage;
        });
    }
}
