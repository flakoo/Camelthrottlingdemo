package org.alpacology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SmsSpammer {

    @Autowired
    private SmsSender smsSender;

    @Scheduled(fixedDelay = 20000000)
    public void spam() {
        for (int i = 0; i < 10; i++) {
            smsSender.sendViaConnector("THIS IS SPAM MESSAGE NO " + i);
        }
    }
}
