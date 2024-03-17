package com.example.bookmyshowapplication.Services.BMSService;

import com.example.bookmyshowapplication.Models.Theatre;
import com.example.bookmyshowapplication.Repository.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {
    private TheatreRepository theatreRepository;

    TheatreService(TheatreRepository theatreRepository){
        this.theatreRepository = theatreRepository;
    }

    public Theatre addTheatre(Theatre theatre){
        return theatreRepository.save(theatre);
    }
}
