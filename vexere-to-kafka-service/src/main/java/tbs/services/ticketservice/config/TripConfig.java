//package tbs.services.ticketservice.config;
//
//import com.fasterxml.jackson.databind.JsonSerializable;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.kafka.connect.json.JsonSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import tbs.services.ticketservice.model.Trip;
//import tbs.services.ticketservice.service.TripSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//// This class is to serialize the object of model class
//
//@Configuration
//public class TripConfig {
//
//    @Bean
//    public ProducerFactory<String, Trip>
//    producerFactory(){
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "127.0.0.1:9092");
//
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                TripSerializer.class);
//
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//
//    }
//
//    @Bean
//    public KafkaTemplate<String, Trip>
//    kafkaTemplate(){
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//
//}
