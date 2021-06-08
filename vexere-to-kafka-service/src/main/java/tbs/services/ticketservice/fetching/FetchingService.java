package tbs.services.ticketservice.fetching;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FetchingService {
    static FileWriter fileWriter;

    public static void sendRequest() throws IOException {
        String result = "";
        URL urlVxr = new URL("http://localhost:8081/trips");
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

                // Parse file into JSONArray


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
