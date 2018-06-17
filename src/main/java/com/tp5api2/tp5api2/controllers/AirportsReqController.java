package com.tp5api2.tp5api2.controllers;

import com.tp5api2.tp5api2.services.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.tp5api2.tp5api2.Tp5api2Application.urlServidor;

@RestController
@RequestMapping("/consultas")
public final class AirportsReqController {

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/getOriginAirports")
    public List getAirportOrigin() throws IOException {

        List returnList = this.restTemplateService.
                            airportswhitorigin(urlServidor);
        return returnList;
    }

    @GetMapping("/getDestinationAirports/{iata}")
    public List getAiportDestination(@PathVariable String iata) {
        List returnList = this.restTemplateService.
                                getdestinationairports(urlServidor, iata);
        return returnList;
    }

    @GetMapping("/getCabinsAndPrices/iataOrigin/{iataOrigin}/iataDestination/{iataDestination}/fecha/{fecha}")
    public List getCabinsAndPrices(@PathVariable("iataOrigin") String iataOrigin,
                                   @PathVariable("iataDestination") String iataDestination,
                                   @PathVariable("fecha") String fecha) {
        List returnList = this.restTemplateService.
                            getCabinsAndPricesWhitRouteandDate(iataOrigin, iataDestination, fecha, urlServidor);
        return returnList;
    }


}
