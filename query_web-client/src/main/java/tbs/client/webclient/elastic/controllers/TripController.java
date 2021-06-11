package tbs.client.webclient.elastic.controllers;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tbs.client.webclient.elastic.service.TripService;
import tbs.client.webclient.model.Trip;

import java.util.List;

@RestController
@RequestMapping("/v1/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Trip> getAllTrips(){
        return tripService.getAllTrips();
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public Trip getTripByCheapestPrice(@PathVariable int price) throws TripNotFoundException{
//        return tripService.getTripByCheapestPrice(price).orElseThrow(new TripNotFoundException("No trip with this price"));
//    }

}
