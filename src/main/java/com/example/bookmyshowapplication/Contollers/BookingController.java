package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Models.Booking;
import com.example.bookmyshowapplication.Models.ResponseStatus;
import com.example.bookmyshowapplication.Services.BookingService;
import com.example.bookmyshowapplication.dtos.BookMovieRequestDto;
import com.example.bookmyshowapplication.dtos.BookMovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();

        try {
            Booking booking = bookingService.makeABooking(
                    bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatId()
            );

            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getPrice());
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){

            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return bookMovieResponseDto;
    }
}
