package org.alpacology.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SmsRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("kacperConnector", "send(${body}");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("tomekConnector", "send(${body}");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("przemoConnector", "send(${body}");
        from("jms:queue:sms")
                .throttle(2)
                .timePeriodMillis(1000)
                .bean("wojtekConnector", "send(${body}");
    }
}
