package tbs.vxrkafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tbs.vxrkafka.kafka.TripConsumer;

import java.util.List;

@SpringBootApplication
@ComponentScan("tbs.client.webclient.*")
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
		System.out.println("Here");
		tripConsumer.getMessages().toString();
		SpringApplication.run(WebClientApplication.class, args);
	}

}
