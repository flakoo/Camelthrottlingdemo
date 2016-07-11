package org.alpacology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SmsSpammer {

    @Autowired
    private SmsSender smsSender;

    private int counter = 1;

    @Scheduled(fixedDelay = 20)
    public void spam() {
        smsSender.sendViaConnector("THIS IS SPAM MESSAGE NO " + counter++);
    }
}
