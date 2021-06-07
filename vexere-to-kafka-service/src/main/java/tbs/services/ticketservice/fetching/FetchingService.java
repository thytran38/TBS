package tbs.services.ticketservice.fetching;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpsParameters;
import sun.net.www.http.HttpClient;
import tbs.services.ticketservice.model.Trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FetchingService {

    public static StringBuffer sendRequest() throws IOException {
        java.lang.String result = "";
        URL urlVxr = new URL("http://localhost:8081/trips");
        java.lang.String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlVxr.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while((readLine = in.readLine()) != null){
                response.append(readLine);
            }
            in.close();
            System.out.println("JSON result: " + response.toString());

        }else{
            System.out.println("NOT WORKED");
        }
        return response;

    }


    public Trip[] parseToPojo(StringBuffer stringBuffer) {
        String jsonString = stringBuffer.toString();
        Gson gson = new Gson();
        Trip[] tripInfo = gson.fromJson(jsonString, Trip[].class);
        System.out.println(Arrays.stream(tripInfo).toArray());
        return tripInfo;

    }
}
