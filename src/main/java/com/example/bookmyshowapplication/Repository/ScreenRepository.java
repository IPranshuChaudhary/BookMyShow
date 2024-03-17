package com.example.bookmyshowapplication.Repository;

import com.example.bookmyshowapplication.Models.LoginStatus;
import com.example.bookmyshowapplication.Models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    Optional<Screen> findById(LoginStatus id);
}
