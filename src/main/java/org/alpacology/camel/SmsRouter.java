package org.alpacology.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsRouter extends RouteBuilder {

    @Autowired
    private ResendPredicate resendPrediacte;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .onWhen(resendPrediacte)
                    .to("jms:queue:sms");
        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "GIVING UP :(");

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
