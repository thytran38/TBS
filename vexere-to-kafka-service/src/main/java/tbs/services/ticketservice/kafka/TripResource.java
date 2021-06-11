package tbs.services.ticketservice.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tbs.services.ticketservice.model.Trip;

@RestController
@RequestMapping("publishTickets")
public class TripResource {

    @Autowired
    private KafkaTemplate<String, Trip> kafkaTemplate;

    private static final String TOPIC = "trip-events";

    // Post Trip using rest
    @GetMapping("/publish/{id}" + "{tripID}/{tripName}/{departLoca}/{desLoca}/{departStation}/{busID}/")
    public String post(
            @PathVariable("tripID") final int tripID,
            @PathVariable("tripName") final String tripName,
            @PathVariable("tripPrice") final int price,
            @PathVariable("departLoca") final String departLoca,
            @PathVariable("desLoca") final String desLoca,
            @PathVariable("budID") final int busID,
            @PathVariable("startTime") final long starttime,
            @PathVariable("arrivalTime") final long arrivalTime){

        kafkaTemplate.send(TOPIC, new Trip(tripID, tripName, price,departLoca, desLoca, starttime, arrivalTime, busID));
        return "Published successfully!";
    }




}
