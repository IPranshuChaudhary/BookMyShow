package com.example.bookmyshowapplication;

import com.example.bookmyshowapplication.Contollers.BookingController;
import com.example.bookmyshowapplication.Contollers.UserController;
import com.example.bookmyshowapplication.Models.LoginStatus;
import com.example.bookmyshowapplication.Services.BookingService;
import com.example.bookmyshowapplication.dtos.BookMovieRequestDto;
import com.example.bookmyshowapplication.dtos.BookMovieResponseDto;
import com.example.bookmyshowapplication.dtos.SignUpRequestDto;
import com.example.bookmyshowapplication.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired
    UserController userController;
    @Autowired
    BookingController bookingController;
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("Pranshuc0@gmail.com");
        signUpRequestDto.setPassword("Pass12");

        SignUpResponseDto signUpResponseDto = userController.signUp(signUpRequestDto);
//        System.out.println(signUpResponseDto.getSignUpStatus());

        if (signUpResponseDto.getSignUpStatus().equals(LoginStatus.SUCCESS)){
            BookMovieRequestDto bookMovieRequestDto = new BookMovieRequestDto();
            bookMovieRequestDto.setShowId(3L);

            List<Long> showSeatId = new ArrayList<>();
            showSeatId.add(1L);
            showSeatId.add(2L);
            showSeatId.add(3L);

            bookMovieRequestDto.setShowSeatId(showSeatId);
            bookMovieRequestDto.setUserId(signUpResponseDto.getUserId());

            BookMovieResponseDto bookMovieResponseDto =
                    bookingController.bookMovie(bookMovieRequestDto);

            System.out.println(bookMovieResponseDto.getBookingId());
            System.out.println(bookMovieResponseDto.getAmount());
            System.out.println(bookMovieResponseDto.getResponseStatus());
//            System.out.println(bookMovieResponseDto.getBookingId());

        }
    }
}
