package tbs.services.ticketservice.fetching;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import tbs.services.ticketservice.model.Trip;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchingService {
    static FileWriter fileWriter;

    public static void getAPIResults() throws IOException {
        String result = "";
        URL urlVxr = new URL("http://localhost:8081/allTrips");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlVxr.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            try {
                // Write to file
                fileWriter = new FileWriter("src/main/resources/apiResults.json");
                fileWriter.write(response.toString());

                // Publish to Kafka

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            in.close();
            System.out.println("JSON result: " + response.toString());

        } else {
            System.out.println("NOT WORKED");
        }
    }


}
