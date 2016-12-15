package org.alpacology.camel;

import org.alpacology.connector.ConnectorException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SmsRouter extends RouteBuilder {

    @Autowired
    private ResendPredicate resendPrediacte;

    @Override
    public void configure() throws Exception {
        from("jms:queue:sms")
                .doTry()
                    .throttle(2)
                    .timePeriodMillis(1000)
                    .bean("inputOutputExceptionSmsConnector", "send(${body}")
                .endDoTry()
                .doCatch(IOException.class)
                    .handled(true)
                        .onWhen(resendPrediacte)
                    .to("jms:queue:sms")
                .doCatch(IOException.class)
                    .handled(true)
                    .log(LoggingLevel.ERROR, "GIVING UP :(");
        from("jms:queue:sms")
                .doTry()
                    .throttle(2)
                    .timePeriodMillis(1000)
                    .bean("connectorExceptionSmsConnector", "send(${body}")
                .endDoTry()
                .doCatch(ConnectorException.class)
                    .handled(true)
                    .onWhen(resendPrediacte)
                        .to("jms:queue:sms")
                .doCatch(ConnectorException.class)
                    .handled(true)
                    .log(LoggingLevel.ERROR, "GIVING UP :(");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("workingSmsConnector", "send(${body}");
    }
}
