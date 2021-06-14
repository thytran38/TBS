package tbs.services.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tbs.services.ticketservice.fetching.FetchingService;

import java.io.IOException;

@SpringBootApplication
public class TicketServiceApplication {

    public static void main(String[] args) throws IOException, IllegalStateException {
        FetchingService fetchingService = new FetchingService();
        fetchingService.getAPIResults();
//
//        TripProducer tripProducer = new TripProducer();
//        tripProducer.publish();

        SpringApplication.run(TicketServiceApplication.class, args);


    }
}
