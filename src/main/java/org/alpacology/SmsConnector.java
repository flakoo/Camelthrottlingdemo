package org.alpacology;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

@Service
public class SmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(SmsConnector.class);

    private String name;

    public void send(String sms) {
        LOGGER.info(name + " is sending SMS: " + sms);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
