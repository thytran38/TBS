package tbs.services.ticketservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import tbs.services.ticketservice.model.Trip;

import java.util.Map;

public class TripDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        Trip trip = null;
        try{
            trip = objectMapper.readValue(data, Trip.class);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
