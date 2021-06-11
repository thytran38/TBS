package tbs.client.webclient.elastic.service.impl;

import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tbs.client.webclient.elastic.repos.TripRepository;
import tbs.client.webclient.elastic.service.TripService;
import tbs.client.webclient.model.Trip;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
