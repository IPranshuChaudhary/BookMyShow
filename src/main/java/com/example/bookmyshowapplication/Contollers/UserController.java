package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Models.User;
import com.example.bookmyshowapplication.Services.UserService;
import com.example.bookmyshowapplication.dtos.SignUpRequestDto;
import com.example.bookmyshowapplication.dtos.SignUpResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bms")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();

        User user = userService.signUp(email, password);

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        signUpResponseDto.setSignUpStatus(user.getLoginStatus());
        signUpResponseDto.setUserId(user.getId());
        return signUpResponseDto;
    }
}
