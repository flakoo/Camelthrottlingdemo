package org.alpacology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;
@Service
public class SmsSender {
    @Bean
    private JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Autowired
    private ConfigurableApplicationContext context;

    public void sendViaConnector(String sms) {
        MessageCreator messageCreator = session -> session.createTextMessage(sms);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.send("sms", messageCreator);
    }
}
