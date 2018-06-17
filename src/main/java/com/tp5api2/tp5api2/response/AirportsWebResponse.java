package com.tp5api2.tp5api2.response;

import com.models.payload.response.AirportResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportsWebResponse {
    private String name;
    private String iataCode;
    public AirportsWebResponse(AirportResponse airport) {
        this.name = airport.getCity().getName() + " ( " + airport.getIata() + " )" + " - " + airport.getName() + " - " + airport.getCity().getState().getCountry().getName();
        this.iataCode = airport.getIata();
    }

}
