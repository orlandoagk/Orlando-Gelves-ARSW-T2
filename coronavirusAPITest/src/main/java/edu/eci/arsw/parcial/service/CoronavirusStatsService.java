package edu.eci.arsw.parcial.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.parcial.persistence.CoronavirusStatsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoronavirusStatsService {
    @Autowired
    HttpConnectionService httpConnectionService;

    @Autowired
    CoronavirusStatsCache coronavirusStatsCache;
    public String getCovidStatsByName(String country){
        String info = null;
        try {
            if(coronavirusStatsCache.estaEnCache(country)){
                info = coronavirusStatsCache.devolverCache(country).getData();
            } else {
                info = httpConnectionService.getCovid19StatsByCountry(country);
                coronavirusStatsCache.cargarCache(country,info);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getCovidStatsAll(){
        String info = null;
        try {
            info = httpConnectionService.getCovid19StatsAll();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return info;
    }
}
