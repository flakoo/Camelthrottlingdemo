package org.alpacology.connector;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SecondWorkingSmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(SecondWorkingSmsConnector.class);

    private String name;

    public void send(String sms) throws Exception {
        LOGGER.info(name + " is sending SMS: " + sms);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
