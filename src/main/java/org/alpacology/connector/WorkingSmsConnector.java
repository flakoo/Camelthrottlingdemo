package org.alpacology.connector;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WorkingSmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(WorkingSmsConnector.class);

    private String name;

    public void send(String sms) throws Exception {
        LOGGER.info(name + " is sending SMS: " + sms);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
