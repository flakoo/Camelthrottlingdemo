package org.alpacology.connector;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InputOutputExceptionSmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(InputOutputExceptionSmsConnector.class);

    private String name;

    public void send(String sms) throws Exception {
        LOGGER.info(name + " is throwing IOException for SMS: " + sms);
        throw new IOException("OOPS");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
