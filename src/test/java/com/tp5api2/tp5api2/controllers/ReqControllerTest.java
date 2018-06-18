package com.tp5api2.tp5api2.controllers;

import com.models.Models.*;
import com.models.payload.response.AirportResponse;
import com.tp5api2.tp5api2.response.AirportsWebResponse;
import com.tp5api2.tp5api2.response.CabinsAndPriceWebResponse;
import com.tp5api2.tp5api2.services.RestTemplateService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.tp5api2.tp5api2.Tp5api2Application.urlServidor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReqControllerTest {

    private ReqController reqController;
    private RestTemplateService restTemplateService;
    private AirportsWebResponse testAirportsWebResponse;

    @Before
    public void config() {
        AirportResponse testAirportResponse = new AirportResponse(new Airports("EZE","EZEIZA",new City("MDQ","Mar del Plata",new State(new Country("Argentina","ARG"),"BSA","Buenos Aires")),(float)50.5,(float)38.2));
        this.testAirportsWebResponse = new AirportsWebResponse(testAirportResponse);
        this.restTemplateService = mock(RestTemplateService.class);
        List<AirportsWebResponse> testList = new ArrayList<>();
        testList.add(this.testAirportsWebResponse);
        try {
            when(this.restTemplateService.airportswhitorigin(urlServidor)).thenReturn(testList);
            when(this.restTemplateService.getdestinationairports(urlServidor, "EZE")).thenReturn(testList);
            when(this.restTemplateService.getCabinsAndPricesWhitRouteandDate("EZE", "FKA", "10-05-2018", urlServidor)).thenReturn(new ArrayList<>());

            when(this.restTemplateService.getdestinationairports(urlServidor, "error")).thenReturn((List<AirportsWebResponse>) new IllegalStateException());
            when(this.restTemplateService.getCabinsAndPricesWhitRouteandDate("error", "FKA", "10-05-2018", urlServidor)).thenReturn((List<CabinsAndPriceWebResponse>) new IllegalStateException());


        }catch (Exception e){

        }
        this.reqController = new ReqController(this.restTemplateService);
    }

    @Test
    public void getAirportOriginTest() {
        try {
            List response = this.reqController.getAirportOrigin();
            assertEquals(response.get(0),this.testAirportsWebResponse);
            verify(this.restTemplateService,times(1)).airportswhitorigin(urlServidor);
        }catch (Exception e){
            assertNotNull(e);
        }
    }
    @Test
    public void getAiportDestinationTestBad() {
        try {
             this.reqController.getAiportDestination("error");
        }catch(Exception e){
            assertNotNull(e);
        }
    }

    @Test
    public void getAiportDestinationTest() {
        try{
            List response = this.reqController.getAiportDestination("EZE");
            verify(this.restTemplateService,times(1)).getdestinationairports(urlServidor,"EZE");
            assertNotNull(response);
        }catch (Exception e){

        }
    }
    @Test
    public void getCabinsAndPricesTest() {
        try{
            List response = this.reqController.getCabinsAndPrices("EZE","FKA","10-05-2018");
            verify(this.restTemplateService,times(1)).getCabinsAndPricesWhitRouteandDate("EZE","FKA","10-05-2018",urlServidor);
            assertNotNull(response);
        }catch(Exception e){

        }
    }
    @Test
    public void getCabinsAndPricesTestBad() {
        try{
            this.reqController.getCabinsAndPrices("error","FKA","10-05-2018");
        }catch(Exception e){
            assertNotNull(e);
        }
    }


}
