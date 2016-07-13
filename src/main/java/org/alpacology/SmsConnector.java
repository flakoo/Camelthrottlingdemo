package org.alpacology;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(SmsConnector.class);

    private String name;

    public void send(String sms) throws Exception {
        Random random = new Random();
        if (random.nextBoolean() == true) {
            LOGGER.info(name + " is sending SMS: " + sms);
        } else {
            LOGGER.warning(name + " failed sending SMS: " + sms);
            throw new Exception();
        }
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
