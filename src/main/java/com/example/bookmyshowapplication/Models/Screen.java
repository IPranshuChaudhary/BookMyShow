package com.example.bookmyshowapplication.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{

    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Seat> seats;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Movie movie;
}
