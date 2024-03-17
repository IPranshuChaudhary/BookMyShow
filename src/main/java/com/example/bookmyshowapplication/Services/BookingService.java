package com.example.bookmyshowapplication.Services;

import com.example.bookmyshowapplication.Exceptions.InvalidShowException;
import com.example.bookmyshowapplication.Exceptions.InvalidUserException;
import com.example.bookmyshowapplication.Exceptions.SeatNotAvailableException;
import com.example.bookmyshowapplication.Models.*;
import com.example.bookmyshowapplication.Repository.BookingRepository;
import com.example.bookmyshowapplication.Repository.ScreenRepository;
import com.example.bookmyshowapplication.Repository.SeatRepository;
import com.example.bookmyshowapplication.Repository.UserRepository;

import com.example.bookmyshowapplication.Utils.PriceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.bookmyshowapplication.Models.SeatStatus.AVAILABLE;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BookingService {
    private UserRepository userRepository;
    private ScreenRepository screenRepository;
    private SeatRepository seatRepository;
    private BookingRepository bookingRepository;


    BookingService(UserRepository userRepository,
                   ScreenRepository screenRepository,
                   SeatRepository seatRepository,
                   BookingRepository bookingRepository
                   ){

        this.userRepository = userRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }
    public Booking makeABooking(Long userId, Long screenId, List<Long> seatIds)
            throws InvalidUserException, InvalidShowException,
            SeatNotAvailableException {


        //1. get user
        //2. get show
        //3. get showSeat
        //4. check if showSeat is available if not throw exception
        //5. if available set showSeat status Blocked
        //6. Save status in DB
        //7. create booking object with pending status
        //8. return booing object

        //1. get user
        Optional<User> optionalUser = userRepository.findById(userId);


        if (optionalUser.isEmpty()){
            throw new InvalidUserException();
        }
        User user = optionalUser.get();

        //2. get screen
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);

        if (optionalScreen.isEmpty()){
            throw new InvalidShowException();
        }
        Screen screen = optionalScreen.get();

        //3. get showSeat
        List<Seat> seats =
                seatRepository.findAllById(seatIds);

        //4. check if showSeat is available if not throw exception
        //5. if available set showSeat status Blocked
        for (Seat seat: seats){

//            System.out.println(showSeat.getSeatStatus());

            if (seat.getSeatStatus().equals(AVAILABLE) ||
                    seat.getSeatStatus() == null){

                seat.setSeatStatus(SeatStatus.BLOCKED);

            }else {
                System.out.println("Reached");
                System.out.println("ERROR");
                throw new SeatNotAvailableException();
            }

        }
        List<Seat> savedSeats = new ArrayList<>();

        //6. Save status in DB
        for (Seat seat: seats){
            savedSeats.add(seatRepository.save(seat));
        }

        int price = PriceCalculator.getPriceCalculatorObj().seatPriceCalculator(seats);

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPrice(price);
        booking.setSeat(savedSeats);
        booking.setTimeOfBooking(new Date());
        booking.setScreen(screen);
        booking.setUser(user);

        booking = bookingRepository.save(booking);

        return booking;
    }

    public Booking updateBookingStatus(String id, String status){
        Long longId = Long.parseLong(id);

        Optional<Booking> optionalBooking = bookingRepository.findById(longId);
        Booking booking = optionalBooking.get();
        if (status.equals("paid")){
            booking.setBookingStatus(BookingStatus.SUCCESS);
            List<Seat> seats = booking.getSeat();

            for (Seat seat: seats){
                seat.setSeatStatus(SeatStatus.BOOKED);
            }
            booking.setSeat(seats);
        }else {
            booking.setBookingStatus(BookingStatus.FAILURE);
            List<Seat> seats = booking.getSeat();

            for (Seat seat: seats){
                seat.setSeatStatus(AVAILABLE);
            }
            booking.setSeat(seats);
        }

        return booking;
    }
}
