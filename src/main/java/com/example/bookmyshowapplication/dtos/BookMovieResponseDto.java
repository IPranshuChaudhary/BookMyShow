package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.Models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {

    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
}
