package com.example.bookmyshowapplication.Utils;


import com.example.bookmyshowapplication.Models.Seat;

import java.util.List;

public class PriceCalculator {
    private static PriceCalculator priceCalculator;
    private PriceCalculator(){};

    public static PriceCalculator getPriceCalculatorObj(){
        if (priceCalculator == null){
            priceCalculator = new PriceCalculator();
        }
        return priceCalculator;
    }

    public int seatPriceCalculator(List<Seat> seats){
        int price = 0;

        for (Seat seat: seats){
            price += seat.getPrice();
        }

        return price;
    }
}
