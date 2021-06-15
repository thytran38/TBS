package tbs.vxrkafka.gui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tbs.vxrkafka.fetching.FetchingService;
import tbs.vxrkafka.service.JSONArrayToEntity;

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
