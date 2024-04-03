package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Models.Booking;
import com.example.bookmyshowapplication.Models.BookingStatus;
import com.example.bookmyshowapplication.Models.ResponseStatus;
import com.example.bookmyshowapplication.Models.Seat;
import com.example.bookmyshowapplication.Services.BookingService;
import com.example.bookmyshowapplication.dtos.BookMovieRequestDto;
import com.example.bookmyshowapplication.dtos.BookMovieResponseDto;
import com.example.bookmyshowapplication.dtos.BookingConfirmationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/bms")
public class BookingController {

    private BookingService bookingService;
    private ServletContext servletContext;

    BookingController(BookingService bookingService, ServletContext servletContext) {
        this.bookingService = bookingService;
        this.servletContext = servletContext;
    }

    @PostMapping("/book")
    @ResponseBody
    public BookMovieResponseDto bookMovie(@RequestBody BookMovieRequestDto bookMovieRequestDto) {

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();


        try {
            Booking booking = bookingService.makeABooking(
                    bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getScreenId(),
                    bookMovieRequestDto.getSeatIds()
            );

            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getPrice());
            bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);


        } catch (Exception e) {
            bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);

            throw new RuntimeException(e);

        }

        return bookMovieResponseDto;
    }

    @GetMapping("/success")
    public String callBackUrl(@RequestParam("razorpay_payment_link_status") String razorpay_payment_link_status,
                              @RequestParam("razorpay_payment_link_reference_id") String razorpay_payment_link_reference_id,
                              Model model) {

        Booking booking = bookingService.updateBookingStatus(
                razorpay_payment_link_reference_id, razorpay_payment_link_status);

        BookingConfirmationDto bookingConfirmationDto = new BookingConfirmationDto();

        if (booking.getBookingStatus() == BookingStatus.SUCCESS) {
            bookingConfirmationDto.setBookingStatus("SUCCESS");
        }else {
            bookingConfirmationDto.setBookingStatus("FAILURE");
        }

        bookingConfirmationDto.setTimeOfBooking(booking.getTimeOfBooking());
        bookingConfirmationDto.setUser(booking.getUser().getEmail());
        bookingConfirmationDto.setPrice(booking.getPrice());

        List<Integer> seatsBooked = new ArrayList<>();

        for (Seat seat: booking.getSeat()){
            seatsBooked.add(seat.getNumber());
        }
        bookingConfirmationDto.setSeats(seatsBooked);
        bookingConfirmationDto.setScreen(booking.getScreen().getName());

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(bookingConfirmationDto);
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error view
            throw new RuntimeException();
        }

        model.addAttribute("jsonResponse", json);

        // Return the name of the view
        return "booking";
    }

}
