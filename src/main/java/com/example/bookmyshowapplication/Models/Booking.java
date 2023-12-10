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
@Component
public class Booking extends BaseModel{

    //SameShow seat could be in cancelled state in one booking
    //while in booked state in another booking
    //so MtoM
    @ManyToMany
    private List<ShowSeat> seats;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToOne
    private User user;
    private int price;
    private Date timeOfBooking;
}
