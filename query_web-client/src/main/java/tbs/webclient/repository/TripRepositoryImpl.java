package tbs.webclient.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import tbs.webclient.model.Trip2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripRepositoryImpl {


    @Autowired
    private ObjectMapper objectMapper;

    RestHighLevelClient client = createClient();

    public static RestHighLevelClient createClient(){

        //////////////////////////
        /////////// IF YOU USE LOCAL ELASTICSEARCH
        //////////////////////////

          String hostname = "localhost";
          RestClientBuilder builder = RestClient.builder(new HttpHost(hostname,9200,"http"));


        //////////////////////////
        /////////// IF YOU USE BONSAI / HOSTED ELASTICSEARCH
        //////////////////////////

//        // replace with your own credentials
//        String hostname = "tbs-search-cluster-5943000335.ap-southeast-2.bonsaisearch.net"; // localhost or bonsai url
//        String username = "FQTrXyR5eY"; // needed only for bonsai
//        String password = "mNkScLMHDPYhdQWroV"; // needed only for bonsai

        // credentials provider help supply username and password
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password));
//
//        RestClientBuilder builder = RestClient.builder(
//                new HttpHost(hostname, 443, "https"))
//                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                    }
//                });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    public List<Trip2> findAllTrips() {
//        client = createClient();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("trips");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        List<Trip2> tripList = new ArrayList<>();
        SearchResponse response = null;
        try{
            response = client.search(searchRequest, RequestOptions.DEFAULT);
            if(response.getHits().getTotalHits().value > 0){
                SearchHit[] searchHit = response.getHits().getHits();
                for(SearchHit hit : searchHit){
                    Map<String, Object> map = hit.getSourceAsMap();
                    System.out.println(map.toString());
                    tripList.add(objectMapper.convertValue(map, Trip2.class));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return tripList;
    }



    public List<Trip2> getTripsByRoute(String fullTripName) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("trips");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("tripName",fullTripName));
        searchRequest.source(searchSourceBuilder);

        List<Trip2> tripList = new ArrayList<>();
        SearchResponse response = null;
        try{
            response = client.search(searchRequest, RequestOptions.DEFAULT);
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
