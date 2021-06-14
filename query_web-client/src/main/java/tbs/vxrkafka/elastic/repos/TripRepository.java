package tbs.vxrkafka.elastic.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tbs.vxrkafka.model.Trip;

import java.util.List;

@Repository
public interface TripRepository extends ElasticsearchRepository<Trip, String> {

    List<Trip> findTripByName(String tripName);

}
