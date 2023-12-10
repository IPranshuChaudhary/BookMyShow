package com.example.bookmyshowapplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    private String language;

    @ManyToOne
    private Screen screen;
}
