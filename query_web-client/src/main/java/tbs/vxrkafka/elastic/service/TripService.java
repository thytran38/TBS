package tbs.vxrkafka.elastic.service;

import tbs.vxrkafka.model.Trip;

import java.util.List;

public interface TripService {

//    Trip getTripByCheapestPrice(int price);

    List<Trip> getAllTrips();

    List<Trip> getTripByRoute(String tripName);




}
