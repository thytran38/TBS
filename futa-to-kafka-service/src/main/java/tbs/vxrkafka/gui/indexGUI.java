package tbs.webclient.gui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tbs.webclient.fetching.FetchingService;
import tbs.webclient.service.JSONArrayToEntity;

import java.io.IOException;

@RestController
@RequestMapping
public class indexGUI{

    @RequestMapping("/tripsFetched")
    public String getFetch() throws IOException {
        FetchingService fetchingService = new FetchingService();
        return FetchingService.getAPIResults().toString();
    }

    @RequestMapping("/trips")
    public String getTrips() throws IOException {
        JSONArrayToEntity jsonArrayToEntity = new JSONArrayToEntity();
        return jsonArrayToEntity.getTripList().toString();
    }





}
