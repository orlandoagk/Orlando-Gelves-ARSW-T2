package edu.eci.arsw.parcial.persistence;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Es una clase que ayudará a manejar el caché en el servidor
 */
@Service
public class CoronavirusStatsCache {
    ConcurrentHashMap<String,CountryCache> cache;

    public CoronavirusStatsCache(){
        cache = new ConcurrentHashMap<>();
    }
    /**
     *
     * @param key Es el nombre del pais el cual se usará como llave para encontrarlo en el caché
     * @return Retorna si el aeropuerto ya se encuentra en caché
     */
    public boolean estaEnCache(String key){
        CountryCache temporal = cache.get(key);
        if (temporal!=null && LocalDateTime.now().isAfter(temporal.tiempoDeCreacion.plusMinutes(5))){
            cache.remove(key);
        }
        return cache.get(key)!=null;
    }

    /**
     *
     * @param key Es el nombre del pais el cual se usará como llave para encontrarlo en el caché
     * @param data Es el JSON en formato de String que se guardará en caché
     */
    public void cargarCache(String key,String data){
        CountryCache temporal = new CountryCache(data);
        cache.put(key,temporal);
    }

    /**
     *
     * @param key Es el nombre del pais el cual se usará como llave para encontrarlo en el caché
     * @return Devuelve el objeto donde esta guardado el tiempo de creación en caché y el objeto JSON mapeado en un String
     */
    public CountryCache devolverCache(String key){

        return cache.get(key);
    }


}
