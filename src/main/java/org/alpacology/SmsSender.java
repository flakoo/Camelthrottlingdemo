package org.alpacology;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
    @Autowired
    private ProducerTemplate producerTemplate;

    public void sendViaConnector(String sms) {
        producerTemplate.sendBody("jms:queue:sms", sms);
    }
}
