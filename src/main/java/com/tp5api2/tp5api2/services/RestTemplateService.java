package com.tp5api2.tp5api2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.models.payload.response.AirportResponse;
import com.models.payload.response.PriceResponse;
import com.tp5api2.tp5api2.response.AirportsWebResponse;
import com.tp5api2.tp5api2.response.CabinsAndPriceWebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public final class RestTemplateService {


    private RestTemplate restTemplate;

    private ObjectMapper mapper;

    public RestTemplateService() {
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    public List<AirportsWebResponse> airportswhitorigin(String url) {
        List dataResult = this.obteinData(url + "/routes/airportswhitorigin");
        return this.formatToAirportWebResponse(dataResult);
    }

    public List<AirportsWebResponse> getdestinationairports(String url, String iata) {
         List dataResult = this.obteinData(url + "/routes/getdestinationairports/" + iata);
         return this.formatToAirportWebResponse(dataResult);
    }
    public List<CabinsAndPriceWebResponse> getCabinsAndPricesWhitRouteandDate(String iataOrigin, String iataDestination, String fecha, String url) {
        List dataResult = this.obteinData(url + "/price/getCabinsAndPrices/iataOrigin/" + iataOrigin + "/iataDestination/" + iataDestination + "/fecha/" + fecha);
        return this.formatToCabinsAndPriceWebResponse(dataResult);
    }
    private List<AirportsWebResponse> formatToAirportWebResponse(List res) {
        List<AirportResponse> airList = mapper.convertValue(res, new TypeReference<List<AirportResponse>>() { });
        List<AirportsWebResponse> webAirList = new ArrayList<>();
        airList.forEach(c -> webAirList.add(new AirportsWebResponse(c)));
        return webAirList;
    }
    private List<CabinsAndPriceWebResponse> formatToCabinsAndPriceWebResponse(List res) {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<PriceResponse> priceList = mapper.convertValue(res, new TypeReference<List<PriceResponse>>() { });
        List<CabinsAndPriceWebResponse> webAirList = new ArrayList<>();
        priceList.forEach(c -> webAirList.add(new CabinsAndPriceWebResponse(c)));
        return webAirList;
    }

    private List obteinData(String url) {
        ResponseEntity<List> response
                = this.restTemplate.getForEntity(url + "/", List.class);
        List res = response.getBody();
       return res;
    }

}
