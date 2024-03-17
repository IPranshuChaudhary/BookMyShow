package com.example.bookmyshowapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

//@EnableJpaAuditing
@SpringBootApplication
//implements CommandLineRunner
public class BookMyShowApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }


//    @Override
//    public void run(String... args) throws Exception {
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("Pranshuc0@gmail.com");
//        signUpRequestDto.setPassword("Pass12");
//
//        SignUpResponseDto signUpResponseDto = userController.signUp(signUpRequestDto);
//        System.out.println(signUpResponseDto.getSignUpStatus());

//        if (signUpResponseDto.getSignUpStatus().equals(LoginStatus.SUCCESS)){
//            BookMovieRequestDto bookMovieRequestDto = new BookMovieRequestDto();
//            bookMovieRequestDto.setShowId(3L);
//
//            List<Long> showSeatId = new ArrayList<>();
//            showSeatId.add(1L);
//            showSeatId.add(2L);
//            showSeatId.add(3L);
//
//            bookMovieRequestDto.setShowSeatId(showSeatId);
//            bookMovieRequestDto.setUserId(signUpResponseDto.getUserId());
//
//            BookMovieResponseDto bookMovieResponseDto =
//                    bookingController.bookMovie(bookMovieRequestDto);
//
//            System.out.println(bookMovieResponseDto.getBookingId());
//            System.out.println(bookMovieResponseDto.getAmount());
//            System.out.println(bookMovieResponseDto.getResponseStatus());
//            System.out.println(bookMovieResponseDto.getBookingId());

//        }
//    }
}
