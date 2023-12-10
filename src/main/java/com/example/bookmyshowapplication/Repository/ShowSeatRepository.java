package com.example.bookmyshowapplication.Repository;

import com.example.bookmyshowapplication.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeats);

    @Modifying
    @Query("update ShowSeat ss set ss.seatStatus =  'AVAILABLE' where ss.id = 1")
    void setSeatStatusForNull();
}
