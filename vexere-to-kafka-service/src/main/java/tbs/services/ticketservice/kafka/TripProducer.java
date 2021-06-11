package tbs.services.ticketservice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tbs.services.ticketservice.model.Trip;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TripProducer {
    static KafkaProducer kafkaProducer;
    static ProducerRecord<String, Trip> producerRecord;
    static final String TOPIC = "trip-events";
    JSONParser jsonParser;


    public void JSONtoObject(FileReader reader) throws IOException, ParseException {
        reader = new FileReader("src/main/resources/apiResults.json");
        jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
//        JSONArray jsonArray =


    }

    public void publish(StringBuffer trips) throws IOException {


    }
}
