package com.example.bookmyshowapplication.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingConfirmationDto {
    private String bookingStatus;

    private List<Integer> seats;

    private String screen;

    private String user;
    private int price;
    private Date timeOfBooking;
}
