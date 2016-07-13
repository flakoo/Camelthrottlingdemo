package org.alpacology;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean(name = "kacperConnector")
    public SmsConnector kacperConnector() {
        return new SmsConnector();
    }
}
