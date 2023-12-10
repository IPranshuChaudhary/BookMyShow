package com.example.bookmyshowapplication.Services;

import com.example.bookmyshowapplication.Exceptions.InvalidShowException;
import com.example.bookmyshowapplication.Exceptions.InvalidUserException;
import com.example.bookmyshowapplication.Exceptions.SeatNotAvailableException;
import com.example.bookmyshowapplication.Models.*;
import com.example.bookmyshowapplication.Repository.ShowRepository;
import com.example.bookmyshowapplication.Repository.ShowSeatRepository;
import com.example.bookmyshowapplication.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;


    @Autowired
    BookingService(UserRepository userRepository,
                   ShowRepository showRepository,
                   ShowSeatRepository showSeatRepository,
                   PriceCalculator priceCalculator
                   ){

        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
    }
    public Booking makeABooking(Long userId, Long showId, List<Long> showSeatId)
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

        //2. get show
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()){
            throw new InvalidShowException();
        }
        Show show = optionalShow.get();

        //3. get showSeat
        List<ShowSeat> optionalShowSeatList =
                showSeatRepository.findAllById(showSeatId);

        //4. check if showSeat is available if not throw exception
        //5. if available set showSeat status Blocked
        for (ShowSeat showSeat: optionalShowSeatList){

//            System.out.println(showSeat.getSeatStatus());

            if (showSeat.getSeatStatus().equals(AVAILABLE) ||
                    showSeat.getSeatStatus() == null){

                showSeat.setSeatStatus(SeatStatus.BLOCKED);

            }else {
                System.out.println("Reached");
                System.out.println("ERROR");
                throw new SeatNotAvailableException();
            }

        }
        List<ShowSeat> savedSeats = new ArrayList<>();

        //6. Save status in DB
        for (ShowSeat showSeat: optionalShowSeatList){
            savedSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setSeats(savedSeats);
        booking.setTimeOfBooking(new Date());
        booking.setShow(show);
        booking.setUser(user);
        booking.setPayments(new ArrayList<>());
        booking.setPrice(priceCalculator.calculatePrice(show, savedSeats));

        return booking;
    }
}
