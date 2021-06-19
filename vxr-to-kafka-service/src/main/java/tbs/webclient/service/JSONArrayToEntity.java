package tbs.webclient.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tbs.webclient.fetching.FetchingService;
import tbs.webclient.model.Trip;

import java.io.IOException;
import java.util.ArrayList;


public class JSONArrayToEntity {
    public static JSONArray jsonArray;
    public ArrayList<Trip> tripList;

    public static JSONArrayToEntity getInstance() {
        JSONArrayToEntity jsonArrayToEntity = new JSONArrayToEntity();
        return jsonArrayToEntity;
    }

    public boolean convert() throws JSONException, ParseException, IOException {
        boolean flag = true;
        String response = FetchingService.getAPIResults();
        jsonArray = new JSONArray(response.toString());
        tripList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONParser parse = new JSONParser(jsonArray.getJSONObject(i).toString());
            System.out.println("Parse" + parse);
            Gson gson = new Gson();

            //Extract JSON string to single strings and set them to the trip's attributes.
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(jsonArray.getJSONObject(i).toString());// response will be the json String
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
            System.out.println("----");
            System.out.println("Test" + tripID + " - " + tripName + " - " + tripPrice + " - " + tripStartTime + " - " + tripBusID + " - " + tripSeatAvaiable + " - " + tripDepart + " - " + tripDes + " - " + depStation);
            System.out.println("----");

            Trip trip = new Trip();
            trip.setTripId(Integer.parseInt(tripID));
            trip.setTripName(tripName);
            trip.setPrice(Integer.parseInt(tripPrice));
            trip.setStartDatetime(Long.parseLong(tripStartTime));
            trip.setSeatAvailable(Integer.parseInt(tripSeatAvaiable));
            trip.setDepartStation(tripDepart);
            trip.setDesLoca(tripDes);
            trip.setBusId(Integer.parseInt(tripBusID));
            trip.setBusBrand(tripBusBrand);
            tripList.add(trip);

            System.out.println("Trip resutl" + trip.getTripId() + " " + trip.getTripName() + " " + trip.getPrice() + " " + trip.getStartDatetime() + " " + trip.getBusId() + " ");

        }
        return flag;
    }

    public ArrayList<Trip> getTripList(){
        return tripList;
    }

    public static JSONArray getTripJsonArray(){
        return jsonArray;
    }

}
