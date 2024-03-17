package com.example.bookmyshowapplication.Contollers;

import com.example.bookmyshowapplication.Models.Theatre;
import com.example.bookmyshowapplication.Services.BMSService.TheatreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bms")
public class BMSController {
    private TheatreService theatreService;

    BMSController(TheatreService theatreService){
        this.theatreService = theatreService;
    }

    @PostMapping("/theatre")
    public Theatre addTheater(@RequestBody Theatre theatre){
        return theatreService.addTheatre(theatre);
    }
}
