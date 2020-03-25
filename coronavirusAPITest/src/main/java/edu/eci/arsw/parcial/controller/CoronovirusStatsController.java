package edu.eci.arsw.parcial.controller;

import edu.eci.arsw.parcial.service.CoronavirusStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/covid19")
public class CoronovirusStatsController {
    @Autowired
    CoronavirusStatsService coronavirusStatsService;

    @RequestMapping(value = "/getCasesByCountry/{country}",method = RequestMethod.GET)
    public ResponseEntity<?> getCasesByCountry(@PathVariable String country){
        String stats = coronavirusStatsService.getCovidStatsByName(country);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @RequestMapping(value="/getAllCases")
    public ResponseEntity<?> getAllCases(){
        String stats = coronavirusStatsService.getCovidStatsAll();
        return new ResponseEntity<>(stats,HttpStatus.OK);
    }

}
