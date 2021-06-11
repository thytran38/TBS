package tbs.client.webclient;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tbs.client.webclient.kafka.TripConsumer;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@ComponentScan("tbs.client.webclient.kafka")
public class WebClientApplication {

	public static void main(String[] args) throws InterruptedException {

		TripConsumer tripConsumer = new TripConsumer();
		Consumer<String, String> consumer = tripConsumer.createConsumer();
		tripConsumer.runConsumer();
		//consumer.subscribe(Collections.singleton("trip-events"));
		List<String> tripList = tripConsumer.getMessages();
		for(String trip:tripList){
			System.out.println(String.format(trip));
		}
		tripConsumer.getMessages();
		SpringApplication.run(WebClientApplication.class, args);
	}

}
