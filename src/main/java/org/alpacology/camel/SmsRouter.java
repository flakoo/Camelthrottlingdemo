package org.alpacology.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SmsRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("jms:queue:dead")
                .maximumRedeliveries(3)
                .redeliveryDelay(1000));

        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("inputOutputExceptionSmsConnector", "send(${body}");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("connectorExceptionSmsConnector", "send(${body}");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("workingSmsConnector", "send(${body}");
    }
}
