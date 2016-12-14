package org.alpacology.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class ResendPredicate implements Predicate {

    public static final int MAX_RESENDS = 3;
    public static final String HEADER_RESENDS = "resends";

    @Override
    public boolean matches(Exchange exchange) {
        Integer resends = (Integer) exchange.getIn().getHeader(HEADER_RESENDS);
        if (resends == null) {
            exchange.getIn().setHeader(HEADER_RESENDS, 1);
            return true;
        } else {
            return increaseResendCounterIfLimitNotReached(exchange, resends);
        }
    }

    private boolean increaseResendCounterIfLimitNotReached(Exchange exchange, Integer resends) {
        if (resends < MAX_RESENDS) {
            exchange.getIn().setHeader(HEADER_RESENDS, ++resends);
            return true;
        } else {
            return false;
        }
    }
}
