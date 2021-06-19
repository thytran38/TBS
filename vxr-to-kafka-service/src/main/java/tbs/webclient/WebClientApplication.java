package tbs.webclient;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tbs.webclient.kafka.TripProducer;
import tbs.webclient.service.JSONArrayToEntity;

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
