package com.example.bookmyshowapplication.Repository;

import com.example.bookmyshowapplication.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {

    Theatre save(Theatre theatre);
}
