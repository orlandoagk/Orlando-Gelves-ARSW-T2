package edu.eci.arsw.parcial.controller;

import edu.eci.arsw.parcial.CoronovirusApiMain;
import edu.eci.arsw.parcial.persistence.CoronavirusStatsException;
import edu.eci.arsw.parcial.service.CoronavirusStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/covid19")
public class CoronovirusStatsController {
    @Autowired
    CoronavirusStatsService coronavirusStatsService;

    /**
     *
     * @param country Es el país el cual quieres consultar su información con respecto al Covid19
     * @return Es una respuesta HTTP
     */
    @RequestMapping(value = "/getCasesByCountry/{country}",method = RequestMethod.GET)
    public ResponseEntity<?> getCasesByCountry(@PathVariable String country){

        try {
            String stats = coronavirusStatsService.getCovidStatsByName(country);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (CoronavirusStatsException e) {
            Logger.getLogger(CoronovirusApiMain.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    /**
     *
     * @return Es una respuesta HTTP
     */
    @RequestMapping(value="/getAllCases")
    public ResponseEntity<?> getAllCases(){
        try {
            String stats = coronavirusStatsService.getCovidStatsAll();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (CoronavirusStatsException e) {
            Logger.getLogger(CoronovirusApiMain.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
