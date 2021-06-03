package tbs.services.ticketservice.fetching;

import com.sun.net.httpserver.HttpsParameters;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchingService {

    public static void sendRequest() throws IOException {
        String result = "";
        URL urlVxr = new URL("http://localhost:8081/trips");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlVxr.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while((readLine = in.readLine()) != null){
                response.append(readLine);
            }
            in.close();
            System.out.println("JSON result: " + response.toString());

        }else{
            System.out.println("NOT WORKED");
        }


    }

}
