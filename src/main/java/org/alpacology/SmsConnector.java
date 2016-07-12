package org.alpacology;

import com.sun.istack.internal.logging.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SmsConnector {
    Logger LOGGER = Logger.getLogger(SmsConnector.class);

    @JmsListener(destination = "sms", containerFactory = "myJmsContainerFactory")
    public void send(String sms) {
        LOGGER.info("Sending SMS: " + sms);
    }
}
