package com.example.bookmyshowapplication.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDto {
    private Long userId;
    private Long screenId;
    private List<Long> seatIds;

}
