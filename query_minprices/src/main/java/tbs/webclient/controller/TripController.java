package tbs.webclient.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tbs.webclient.model.Trip2;
import tbs.webclient.service.TripService;
//import tbs.webclient.service.TripService;

import java.util.List;

@RestController

public class TripController {

//    @Autowired
//    private TripService tripService;
//
//    @Bean
//    public TripService tripService(){return new TripService();}

    @Cacheable(value="cheapTrips",key="#tripRoute")
    @RequestMapping(value = "/minprice", method= RequestMethod.GET)
    public List<Trip2> endpoint(@RequestParam(required = false, name= "route") String tripRoute) {

        TripService tripService = new TripService();
        System.out.print("TripRepo " + tripService.cheapTripsByRoute(tripRoute));

        return tripService.cheapTripsByRoute(tripRoute);
    }


}
