package tbs.kafka.EventStream.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {

    @KafkaListener(topics = "test", groupId = "mygroup")
    public void consumefromTopic(String message){
        System.out.println("Consumed messages: " + message);
    }
}
