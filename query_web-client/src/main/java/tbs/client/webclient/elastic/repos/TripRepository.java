package tbs.client.webclient.elastic.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tbs.client.webclient.model.Trip;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends ElasticsearchRepository<Trip, String> {

    List<Trip> findTripByName(String tripName);

}
