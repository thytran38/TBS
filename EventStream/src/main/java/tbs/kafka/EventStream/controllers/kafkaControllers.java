package tbs.kafka.EventStream.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tbs.kafka.EventStream.services.EventConsumer;
import tbs.kafka.EventStream.services.EventProducer;

@RestController
@RequestMapping("/events")
public class kafkaControllers {

    @Bean
    public EventProducer eventConsumer(){
        return new EventProducer();
    }

    @Autowired
    private EventProducer eventProducer;

    @PostMapping(value="/post")
    public void sendMessage(@RequestParam("msg") String msg){
        eventProducer.publishtoTopic(msg);
    }
}
