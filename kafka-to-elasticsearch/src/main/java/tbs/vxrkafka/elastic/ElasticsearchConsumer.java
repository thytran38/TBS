package tbs.vxrkafka.elastic;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class ElasticsearchConsumer {
    public static RestHighLevelClient createClient(){

        //////////////////////////
        /////////// IF YOU USE LOCAL ELASTICSEARCH
        //////////////////////////

        //  String hostname = "localhost";
        //  RestClientBuilder builder = RestClient.builder(new HttpHost(hostname,9200,"http"));


        //////////////////////////
        /////////// IF YOU USE BONSAI / HOSTED ELASTICSEARCH
        //////////////////////////

        // replace with your own credentials
        String hostname = "tbs-search-cluster-5943000335.ap-southeast-2.bonsaisearch.net"; // localhost or bonsai url
        String username = "6jp5NBqZnH"; // needed only for bonsai
        String password = "R8UJQiFHkE4nyzBmxWN"; // needed only for bonsai

        // credentials provider help supply username and password
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost(hostname, 443, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    public static KafkaConsumer<String, String> createConsumer(String topic){

        String bootstrapServers = "127.0.0.1:9092";
        String groupID = "elastic-group-04";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // disable auto commit of offsets
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "200"); // disable auto commit of offsets

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;

    }

    private static JsonParser jsonParser = new JsonParser();

    private static String extractIdFromTrips(String tripJSON){
        // gson library
        return jsonParser.parse(tripJSON)
                .getAsJsonObject()
                .get("tripID")
                .getAsString();
    }

    public static void runTask() throws IOException {
        Logger logger = LoggerFactory.getLogger(ElasticsearchConsumer.class.getName());
        RestHighLevelClient client = createClient();
        KafkaConsumer<String, String> consumer = createConsumer("trips");
        final int giveUp = 100;
        int noRecordsCount = 0;

        consumer.subscribe(Collections.singleton("trips"));

        while(true){
            ConsumerRecords<String, String> consumerRecords =
                    consumer.poll(Duration.ofMillis(300)); // new in Kafka 2.0.0

            Integer recordCount = consumerRecords.count();
            logger.info("Received " + recordCount + " records");

            BulkRequest bulkRequest = new BulkRequest();

            for (ConsumerRecord<String, String> record : consumerRecords){

                // 2 strategies
                // kafka generic ID
                // String id = record.topic() + "_" + record.partition() + "_" + record.offset();

                try {
                    //String id = extractIdFromTrips(record.value());

                    // where we insert data into ElasticSearch
                    Map<String, String> jsonMap = new HashMap<>();
                    JsonParser parser = new JsonParser();
                    JsonObject object = (JsonObject) parser.parse(record.value());
                    String tripID = object.get("tripID").getAsString();
                    String tripName = object.get("tripName").getAsString();
                    String tripPrice = object.get("price").getAsString();
                    String tripStartTime = object.get("startDatetime").getAsString();
                    String tripBusID = object.get("busID").getAsString();
                    String tripBusBrand = object.get("busBrand").getAsString();
                    String tripSeatAvaiable = object.get("seatAvailable").getAsString();
                    String tripDepart = object.get("departLocation").getAsString();
                    String tripDes = object.get("desLoca").getAsString();
                    String depStation = object.get("departStation").getAsString();

                    jsonMap.put("tripId", tripID);
                    jsonMap.put("tripName", tripName);
                    jsonMap.put("tripPrice", tripPrice);
                    jsonMap.put("tripStartTime", tripStartTime);
                    jsonMap.put("tripBusID", tripBusID);
                    jsonMap.put("tripBusBrand", tripBusBrand);
                    jsonMap.put("tripSeatAvailable", tripSeatAvaiable);
                    jsonMap.put("tripDepart", tripDepart);
                    jsonMap.put("tripDes", tripDes);
                    jsonMap.put("depStation", depStation);

                    IndexRequest indexRequest = new IndexRequest("trips").id(record.key())
                            .source(jsonMap, XContentType.JSON);
                           // .id(id); // this is to make our consumer idempotent

                    bulkRequest.add(indexRequest); // we add to our bulk request (takes no time)
                } catch (NullPointerException e){
                    logger.warn("skipping bad data: " + record.value());
                }

            }

            if (recordCount > 0) {
                BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
                logger.info("Committing offsets...");
                consumer.commitSync();
                logger.info("Offsets have been committed");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // close the client gracefully
       // client.close();

    }
}
