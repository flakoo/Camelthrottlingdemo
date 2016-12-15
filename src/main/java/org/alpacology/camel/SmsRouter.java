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
                .onException(IOException.class)
                    .handled(true)
                .onException(RejectedExecutionException.class)
                    .handled(true)
                    .to("direct:workingRoute")
                .end()
                .throttle(2)
                .timePeriodMillis(1000)
                .loadBalance()
                    .circuitBreaker(2, 2000L, IOException.class)
                        .inheritErrorHandler(false)
                .to("bean:inputOutputExceptionSmsConnector?method=send(${body}");
        from("jms:queue:sms")
                .onException(ConnectorException.class)
                    .handled(true)
                .onException(RejectedExecutionException.class)
                    .handled(true)
                    .to("direct:workingRoute")
                .end()
                .throttle(2)
                .timePeriodMillis(1000)
                .loadBalance()
                    .circuitBreaker(2, 2000L, ConnectorException.class)
                        .inheritErrorHandler(false)
                .to("bean:connectorExceptionSmsConnector?method=send(${body}");

        from("direct:workingRoute")
                .throttle(2)
                .timePeriodMillis(1000)
                .to("bean:workingSmsConnector?method=send(${body}");
    }
}
