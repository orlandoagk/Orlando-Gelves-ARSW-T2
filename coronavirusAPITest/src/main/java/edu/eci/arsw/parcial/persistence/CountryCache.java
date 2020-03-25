package edu.eci.arsw.parcial.persistence;

import java.time.LocalDateTime;

/**
 * Es una clase para poder manejar el caché de manera más fácil y eficiente
 */
public class CountryCache {
    private String data;
    public LocalDateTime tiempoDeCreacion;

    public CountryCache(String data){
        this.data = data;
        tiempoDeCreacion = LocalDateTime.now();
    }

    public String getData(){
        return data;
    }
}
