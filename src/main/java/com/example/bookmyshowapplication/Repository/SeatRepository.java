package com.example.bookmyshowapplication.Repository;

import com.example.bookmyshowapplication.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllById(Long id);

    Seat save(Seat seat);
}
