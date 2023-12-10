package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Models.User;
import com.example.bookmyshowapplication.Services.UserService;
import com.example.bookmyshowapplication.dtos.SignUpRequestDto;
import com.example.bookmyshowapplication.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();

        User user = userService.signUp(email, password);

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        signUpResponseDto.setSignUpStatus(user.getLoginStatus());
        signUpResponseDto.setUserId(user.getId());
        return signUpResponseDto;
    }
}
