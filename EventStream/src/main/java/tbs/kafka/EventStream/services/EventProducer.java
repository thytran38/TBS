package tbs.kafka.EventStream.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    public final String topic = "test";

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void publishtoTopic(String message){
        System.out.println("Publishing to topic: " + topic);
        this.kafkaTemplate.send(topic, message);

    }
}
