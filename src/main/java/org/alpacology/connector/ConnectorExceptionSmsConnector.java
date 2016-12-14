package org.alpacology.connector;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConnectorExceptionSmsConnector implements BeanNameAware{
    Logger LOGGER = Logger.getLogger(ConnectorExceptionSmsConnector.class);

    private String name;

    public void send(String sms) throws Exception {
        LOGGER.info(name + " is throwing IOException for SMS: " + sms);
        throw new ConnectorException("OOPS");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
