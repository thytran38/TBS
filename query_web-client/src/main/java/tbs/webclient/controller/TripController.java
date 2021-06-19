package tbs.webclient.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tbs.webclient.model.Trip2;
import tbs.webclient.repository.TripRepositoryImpl;
//import tbs.webclient.service.TripService;

import java.util.List;

@RestController
public class TripController {

    TripRepositoryImpl tripRepositoryImpl = new TripRepositoryImpl();

    @Cacheable(value = "trips")
    @RequestMapping(value = "/trips", method= RequestMethod.GET)
    public List<Trip2> endpoint() {

//        TripRepositoryImpl tripRepositoryImpl = new TripRepositoryImpl();
        System.out.print("TripRepo " + tripRepositoryImpl.findAllTrips());

        return tripRepositoryImpl.findAllTrips();
    }

    @Cacheable(value="trips",key="#name")
    @RequestMapping(value="/trips/search", method=RequestMethod.GET)
    public List<Trip2> getSingleTrip(@RequestParam(required = false, name= "name") String name){
        System.out.print("Trip By Route " + tripRepositoryImpl.getTripsByRoute(name));

        return tripRepositoryImpl.getTripsByRoute(name);
    }




}
