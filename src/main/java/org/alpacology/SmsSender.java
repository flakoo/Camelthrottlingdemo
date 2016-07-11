package org.alpacology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    @Autowired
    private SmsConnector smsConnector;

    public void sendViaConnector(String sms) {
        smsConnector.send(sms);
    }
}
