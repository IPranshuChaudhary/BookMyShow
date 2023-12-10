package com.example.bookmyshowapplication.Services;

import com.example.bookmyshowapplication.Models.Show;
import com.example.bookmyshowapplication.Models.ShowSeat;
import com.example.bookmyshowapplication.Models.ShowSeatType;
import com.example.bookmyshowapplication.Repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats){

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        for (ShowSeat showSeat: showSeats){

            for (ShowSeatType showSeatType: showSeatTypes){

                if (showSeat.getSeat().getSeatType().equals(showSeatType)){
                    amount += showSeatType.getPrice();
                }
            }
        }
        return amount;
    }


}
