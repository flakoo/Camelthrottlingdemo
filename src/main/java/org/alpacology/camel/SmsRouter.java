package org.alpacology.camel;

import org.alpacology.connector.ConnectorException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SmsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .loadBalance()
                    .failover(IOException.class)
                        .inheritErrorHandler(false)
                    .to(
                            "bean:inputOutputExceptionSmsConnector?method=send(${body}",
                            "direct:workingRoute"
                    );
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .loadBalance()
                .failover(ConnectorException.class)
                    .inheritErrorHandler(false)
                .to(
                        "bean:connectorExceptionSmsConnector?method=send(${body}",
                        "direct:workingRoute"
                );

        from("direct:workingRoute")
                .throttle(2)
                .timePeriodMillis(1000)
                .to("bean:workingSmsConnector?method=send(${body}");
    }
}
