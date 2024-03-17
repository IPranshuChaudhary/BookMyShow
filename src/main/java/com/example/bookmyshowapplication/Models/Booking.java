package com.example.bookmyshowapplication.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    //SameShow seat could be in cancelled state in one booking
    //while in booked state in another booking
    //so MtoM

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @OneToMany
    private List<Seat> seat;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private User user;
    private int price;
    private Date timeOfBooking;
}
