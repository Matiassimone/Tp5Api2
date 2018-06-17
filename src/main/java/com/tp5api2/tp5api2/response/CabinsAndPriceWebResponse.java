package com.tp5api2.tp5api2.response;

import com.models.payload.response.PriceResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CabinsAndPriceWebResponse {
    private String cabinName;
    private float price;
    public CabinsAndPriceWebResponse(PriceResponse priceResponse) {
        this.cabinName = priceResponse.getCabin().getName();
        this.price = priceResponse.getPrice();
    }

}
