package tbs.vxrkafka.elastic.service.impl;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import tbs.vxrkafka.elastic.repos.TripRepository;
import tbs.vxrkafka.elastic.service.TripService;
import tbs.vxrkafka.model.Trip;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTripService implements TripService {

    private final TripRepository tripRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public DefaultTripService(TripRepository tripRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.tripRepository = tripRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

//    @Override
//    public Trip getTripByCheapestPrice(int price) {
//        BoolQueryBuilder criteria = QueryBuilders.boolQuery();
////        criteria.must().add
//        return elasticsearchRestTemplate.queryForObject(new NativeSearchQueryBuilder().withQuery(criteria).build(), Trip.class);
//    }

    @Override
    public List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<>();
        tripRepository.findAll().forEach(trip -> trips.add(trip));
        return trips;
    }

    @Override
    public List<Trip> getTripByRoute(String tripName) {
        return tripRepository.findTripByName(tripName);
    }
}
