package org.alpacology;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean(name = "kacperConnector")
        public SmsConnector kacperConnector() {
            return new SmsConnector();
    }

    @Bean(name = "tomekConnector")
    public SmsConnector tomekConnector() {
        return new SmsConnector();
    }

    @Bean(name = "przemoConnector")
    public SmsConnector przemoConnector() {
        return new SmsConnector();
    }

    @Bean(name = "wojtekConnector")
    public SmsConnector wojtekConnector() {
        return new SmsConnector();
    }
}
