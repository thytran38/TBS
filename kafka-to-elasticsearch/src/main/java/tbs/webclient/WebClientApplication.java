package tbs.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tbs.webclient.elastic.ElasticsearchConsumer;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("tbs.client.webclient.*")
public class WebClientApplication {

	public static void main(String[] args) throws InterruptedException, IOException {

//		TripConsumer tripConsumer = new TripConsumer();
//		tripConsumer.runConsumer();

		ElasticsearchConsumer consumer1 = new ElasticsearchConsumer();
		consumer1.createClient();
		consumer1.runTask();

		SpringApplication.run(WebClientApplication.class, args);
	}

}
