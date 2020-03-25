package edu.eci.arsw.parcial.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.parcial.models.Country;
import edu.eci.arsw.parcial.persistence.CoronavirusStatsCache;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

/**
 * Es una clase que permite utilizar los servicios de la aplicación de Covid19
 */
@Service
public class CoronavirusStatsService {
    @Autowired
    HttpConnectionService httpConnectionService;

    @Autowired
    CoronavirusStatsCache coronavirusStatsCache;

    /**
     *
     * @param country Es el pais del cual quiere conseguir información con respecto al Covid19
     * @return Retorna un String en formato JSON con la información del pais puesto
     */
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

    /**
     *
     * @return Retorna un String en formato JSON el cual tendrá la información de todos los paises contagiados por el virus Covid19
     */
    public String getCovidStatsAll(){
        String info = null;
        try {
            info = httpConnectionService.getCovid19StatsAll();
            System.out.println(info);
            JSONArray jsonAMandar = new JSONArray();
            JSONObject json = new JSONObject(info);
            JSONObject data = new JSONObject(json.get("data").toString());
            JSONArray covid19Stats = new JSONArray(data.get("covid19Stats").toString());
            HashMap<String, Country> countryHashMap = new HashMap<>();
            for(int i = 0;i<covid19Stats.length();i++){
                JSONObject tmp = (JSONObject) covid19Stats.get(i);
                if(countryHashMap.containsKey(tmp.get("country").toString())){
                    countryHashMap.get(tmp.get("country").toString()).sumarJSON(tmp);
                } else {
                    countryHashMap.put(tmp.get("country").toString(),new Country(tmp));
                }
            }


            for(String string:countryHashMap.keySet()){
                jsonAMandar.put(countryHashMap.get(string).getJsonObject());
            }
            info=jsonAMandar.toString();




        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return info;
    }
}
