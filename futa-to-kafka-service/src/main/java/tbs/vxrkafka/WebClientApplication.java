package tbs.vxrkafka;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tbs.vxrkafka.kafka.TripProducer;
import tbs.vxrkafka.service.JSONArrayToEntity;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("tbs.client.webclient.*")
public class WebClientApplication {

	public static void main(String[] args) throws InterruptedException, ParseException, IOException, JSONException {
		JSONArrayToEntity jsonArray = JSONArrayToEntity.getInstance();
		if(jsonArray.convert()){
			System.out.println("Run");
		}
		TripProducer tripProducer = new TripProducer();
		tripProducer.serverCallback();
		SpringApplication.run(WebClientApplication.class, args);
	}

}
