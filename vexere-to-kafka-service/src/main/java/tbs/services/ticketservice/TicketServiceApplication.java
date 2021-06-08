package tbs.services.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tbs.services.ticketservice.fetching.FetchingService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class TicketServiceApplication {

	public static void main(String[] args) throws IOException, IllegalStateException {
		FetchingService fetchingService = new FetchingService();
		fetchingService.sendRequest();
		SpringApplication.run(TicketServiceApplication.class, args);


	}



}
