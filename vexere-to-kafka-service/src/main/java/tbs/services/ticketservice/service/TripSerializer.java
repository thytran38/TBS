package tbs.services.ticketservice.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import tbs.services.ticketservice.model.Trip;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class TripSerializer implements Serializer {


    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] returnValue = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            returnValue = objectMapper.writeValueAsString(topic).getBytes();

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }


    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
