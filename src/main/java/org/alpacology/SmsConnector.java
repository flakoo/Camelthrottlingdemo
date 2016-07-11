package org.alpacology;

import com.sun.istack.internal.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class SmsConnector {
    Logger LOGGER = Logger.getLogger(SmsConnector.class);

    public void send(String sms) {
        LOGGER.info("Sending SMS: " + sms);
    }

}
