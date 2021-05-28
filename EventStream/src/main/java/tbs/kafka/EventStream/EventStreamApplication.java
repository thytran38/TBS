package tbs.kafka.EventStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"tbs.kafka.EventStream.controllers"})
public class EventStreamApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventStreamApplication.class, args);

	}

}
