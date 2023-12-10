package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.Models.LoginStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private Long userId;
    private LoginStatus signUpStatus;
}
