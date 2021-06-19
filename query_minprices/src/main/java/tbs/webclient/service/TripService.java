package tbs.webclient.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import tbs.elasticsearch.client;
import tbs.webclient.model.Trip;
import tbs.webclient.model.Trip2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TripService {

    @Autowired
    private ObjectMapper objectMapper;

    RestHighLevelClient rclient = client.createClient();

    @Cacheable(value = "cheapTrips", key="#fullTripName")
    public List<Trip2> cheapTripsByRoute(String fullTripName) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("prices");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("tripName",fullTripName));
        searchSourceBuilder.sort("tripPrice.keyword",SortOrder.ASC);
        searchRequest.source(searchSourceBuilder);

        List<Trip2> tripList = new ArrayList<>();
        SearchResponse response = null;
        try{
            response = rclient.search(searchRequest, RequestOptions.DEFAULT);
            if(response.getHits().getTotalHits().value > 0){
                SearchHit[] searchHit = response.getHits().getHits();
                for(SearchHit hit : searchHit){
                    Map<String, Object> map = hit.getSourceAsMap();
                    System.out.println("Map to string by route" + map.toString());
                    tripList.add(objectMapper.convertValue(map, Trip2.class));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return tripList;
    }

}
