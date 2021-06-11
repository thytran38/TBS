package tbs.client.webclient.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.connect.data.Timestamp;
import org.apache.tomcat.jni.Time;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class TripConsumer {
    FileWriter fileWriter;

    public static Consumer<String, String> createConsumer() {
        final Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        final Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;
    }

    public static void runConsumer() throws InterruptedException {
        final Consumer<String, String> consumer = createConsumer();

        final int giveUp = 100;
        int noRecordsCount = 0;

        consumer.subscribe(Collections.singleton("trip-events"));
        while (true) {
            final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(200));

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consume Record: (%d, %s, %d, %d) \n ", record.key(), record.value(), record.partition(), record.offset());

            });
            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("Done");

    }

    @KafkaListener(topics = "trip-events", groupId = "group1")
    public void listen(String message) {
        System.out.println("Received mess" + message);
    }



    public List<String> getMessages() {
        List<String> messages = new ArrayList<>();

        try {
            Map<String, Object> props = new HashMap<>();
//            properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//            properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
//            properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//            properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//            properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
//            properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//            properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "group1"); // default topic name
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

            //Kafka Consumer subscribes list of topics here.
            consumer.subscribe(Arrays.asList("trip-events"));  // replace you topic name

            //print the topic name

            java.util.Map<String,java.util.List<PartitionInfo>> listTopics = consumer.listTopics();
            System.out.println("list of topic size :" + listTopics.size());

            for(String topic : listTopics.keySet()){
                System.out.println("topic name :"+topic);
            }

            consumer.subscribe(Arrays.asList("trip-events"));
            consumer.poll(Duration.ofMillis(100));
            consumer.seekToBeginning(consumer.assignment());
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                messages.add(String.format("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));
                System.out.println(String.format("This record: %d has %s and %s%n", record.offset(), record.key(), record.value()));
                try {
                    fileWriter = new FileWriter("src/main/resources/kafkaMessages.json");
                    fileWriter.write(String.valueOf(TimerTask.class.toString()));
                    fileWriter.write(String.format("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();


        }
        return messages;

    }
}