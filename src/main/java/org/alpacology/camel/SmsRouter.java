package org.alpacology.camel;

import org.alpacology.connector.ConnectorException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.RejectedExecutionException;

@Component
public class SmsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:sms")
                .onException(ConnectorException.class)
                    .handled(true)
                .onException(RejectedExecutionException.class)
                    .handled(true)
                    .to("jms:queue:sms")
                .end()
                .throttle(2)
                    .timePeriodMillis(1000)
                    .loadBalance()
                        .circuitBreaker(1, 100000L, ConnectorException.class)
                        .inheritErrorHandler(false)
                    .to("bean:connectorExceptionSmsConnector?method=send(${body}");

        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .to("bean:secondWorkingSmsConnector?method=send(${body}");

        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .to("bean:workingSmsConnector?method=send(${body}");
    }
}
