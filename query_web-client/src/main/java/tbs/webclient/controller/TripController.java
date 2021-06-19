package tbs.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tbs.webclient.model.Trip2;
import tbs.webclient.repo.TripRepositoryImpl;
import tbs.webclient.model.Trip;
//import tbs.webclient.service.TripService;

import java.util.List;

@RestController
public class TripController {

    TripRepositoryImpl tripRepositoryImpl = new TripRepositoryImpl();

    @RequestMapping(value = "/trips", method= RequestMethod.GET)
    public List<Trip2> endpoint() {

//        TripRepositoryImpl tripRepositoryImpl = new TripRepositoryImpl();
        System.out.print("TripRepo " + tripRepositoryImpl.findAllTrips());

        return tripRepositoryImpl.findAllTrips();
    }

    @RequestMapping(value="/trips/search", method=RequestMethod.GET)
    public List<Trip2> getSingleTrip(@RequestParam(required = false, name= "name") String name){
        System.out.print("Trip By Route " + tripRepositoryImpl.getTripsByRoute(name));

        return tripRepositoryImpl.getTripsByRoute(name);
    }




}
