package com.example.bookmyshowapplication.Services;

import com.example.bookmyshowapplication.Models.LoginStatus;
import com.example.bookmyshowapplication.Models.User;
import com.example.bookmyshowapplication.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User signUp(String email, String password){
        User user;
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()){
            user = optionalUser.get();
            boolean status = login(email, password);
            if (status){
                user.setLoginStatus(LoginStatus.SUCCESS);
            }else {
                user.setLoginStatus(LoginStatus.FAILURE);
            }

        }else {
            user = new User();
            user.setEmail(email);
            user.setBookings(new ArrayList<>());
            user.setLoginStatus(LoginStatus.SUCCESS);

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encriptedPass = bCryptPasswordEncoder.encode(password);
            user.setPassword(encriptedPass);

            userRepository.save(user);
        }
        return user;
    }

    public boolean login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);

        String storedPassword = user.get().getPassword();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, storedPassword);
    }
}
