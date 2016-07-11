package org.alpacology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CamelThrottlingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelThrottlingDemoApplication.class, args);
	}
}
