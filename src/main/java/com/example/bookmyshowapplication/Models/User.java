package com.example.bookmyshowapplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String name;
    private String email;
    private String password;
    private LoginStatus loginStatus;

    // 1:m
    @OneToMany
    private List<Booking> bookings;
}
