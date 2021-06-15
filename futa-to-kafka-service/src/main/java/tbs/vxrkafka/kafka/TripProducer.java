package tbs.vxrkafka.kafka;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tbs.vxrkafka.service.JSONArrayToEntity;

import java.util.Properties;

public class TripProducer {

    public void serverCallback() throws JSONException {
        Logger logger = LoggerFactory.getLogger(TripProducer.class);
        String bootstrapServer = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        JSONArray array = JSONArrayToEntity.getTripJsonArray();
        for(int i = 0; i<array.length(); i++){
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(array.getJSONObject(i).toString());
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("trip-events",object.toString());

            // Send data - asynchronously
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                    // Execute everytime a record is successfully sent or thrown exception
                    if(exception != null){
                        // Record successfully sent
                        logger.info("Received new metadata. \n" + "Topic:" + recordMetadata.topic() +
                                "\n" + "Partition: " + recordMetadata.partition() +
                                "Offset: " +recordMetadata.offset() +
                                "Timestamp: " + recordMetadata.timestamp());

                    }else{
                        logger.error("Error while producing", exception);
                    }

                }
            });
        }

        producer.flush();
        producer.close();



    }

}
