package edu.eci.arsw.parcial.persistence;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CoronavirusStatsCache {
    ConcurrentHashMap<String,CountryCache> cache;

    public CoronavirusStatsCache(){
        cache = new ConcurrentHashMap<>();
    }
    public boolean estaEnCache(String key){
        CountryCache temporal = cache.get(key);
        if (temporal!=null && LocalDateTime.now().isAfter(temporal.tiempoDeCreacion.plusMinutes(5))){
            cache.remove(key);
        }
        return cache.get(key)!=null;
    }


    public void cargarCache(String key,String data){
        CountryCache temporal = new CountryCache(data);
        cache.put(key,temporal);
    }


    public CountryCache devolverCache(String key){

        return cache.get(key);
    }


}
