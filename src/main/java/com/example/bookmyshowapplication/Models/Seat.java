package com.example.bookmyshowapplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{

    @ManyToOne
    private SeatType seatType;
    private String number;
    private int rowNum;
    private int colNum;
}
