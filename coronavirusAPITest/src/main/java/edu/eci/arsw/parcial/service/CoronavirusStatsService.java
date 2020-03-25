package edu.eci.arsw.parcial.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.parcial.persistence.CoronavirusStatsCache;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
                JSONObject json = new JSONObject(info);
                JSONObject data = new JSONObject(json.get("data").toString());
                JSONArray covid19Stats = new JSONArray(data.get("covid19Stats").toString());
                JSONArray nuevoCovid19Stats = new JSONArray();
                String longLat = httpConnectionService.getLatLongByCountry(country);
                JSONArray longLatTemp = new JSONArray(longLat);
                JSONObject longLatObj = new JSONObject(longLatTemp.get(0).toString());
                JSONArray latlng = new JSONArray(longLatObj.get("latlng").toString());
                for(int i=0; i<covid19Stats.length();i++){
                    JSONObject temp = (JSONObject) covid19Stats.get(i);
                    temp.put("coord",new JSONObject("{\"latitud\":"+latlng.get(0).toString()+",\"longitud\":"+latlng.get(1).toString()+"}"));
                    nuevoCovid19Stats.put(temp);
                }
                String infoReal = nuevoCovid19Stats.toString();
                System.out.println(infoReal);
                coronavirusStatsCache.cargarCache(country,infoReal);
                info = infoReal;
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
