package com.tp5api2.tp5api2.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Models.Airports;
import com.models.Models.City;
import com.models.Models.Country;
import com.models.Models.State;
import com.models.payload.response.AirportResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.tp5api2.tp5api2.Tp5api2Application.urlServidor;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RestTemplateServiceTest {

    @Autowired
    private RestTemplateService restTemplateService;


    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void initialConfig() throws Exception {

        this.server = MockRestServiceServer.createServer(new RestTemplate());
        String airportString =
                objectMapper.writeValueAsString(new AirportResponse
                        (new Airports("EZE","EZEIZA",new City("MDQ","Mar del Plata",new State(new Country("Argentina","ARG"),"BSA","Buenos Aires")),(float)50.5,(float)38.2)));
        this.server.expect(requestTo(urlServidor + "/routes/airportswhitorigin/)")).andRespond(withSuccess(airportString, MediaType.APPLICATION_JSON));


    }
    /*
    @Test
    public void airportswhitoriginTest() throws Exception {
        List list = this.restTemplateService.airportswhitorigin(urlServidor);
    }
    */

}
