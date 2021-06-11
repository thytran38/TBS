package tbs.client.webclient.elastic.service;

import tbs.client.webclient.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {

//    Trip getTripByCheapestPrice(int price);

    List<Trip> getAllTrips();

    List<Trip> getTripByRoute(String tripName);




}
