package edu.eci.arsw.parcial.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

/**
 * Esta clase permite hacer peticiones HTTP REST utilizando sus métodos
 */
@Service
public class HttpConnectionService {

    /**
     *
     *
     * @param country Es el pais del cual quiere conseguir información con respecto al Covid19
     * @return Retorna un String en formato JSON con toda la información con respecto al Covid19 el pais seleccionado
     * @throws UnirestException
     */
    public String getCovid19StatsByCountry(String country) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country="+country)
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "0e72bcd144msh8951afd83016cbbp115df0jsnf8ca2e9da0ec")
                .asString();
        return response.getBody();
    }

    /**
     *
     * @return Retorna un String con JSON que contiene toda la información de todos los paises que estan contagiados con Covid19
     * @throws UnirestException Es una excepción de UniRest para cuando encuentra un problema en el request
     */
    public String getCovid19StatsAll() throws UnirestException{
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "0e72bcd144msh8951afd83016cbbp115df0jsnf8ca2e9da0ec")
                .asString();
        return response.getBody();
    }

    /**
     *
     * @param country Es el pais del cual quiere conseguir información general
     * @return Retorna un String en formato JSON que contiene la información del pais
     * @throws UnirestException Es una excepción de UniRest para cuando encuentra un problema en el request
     */
    public String getLatLongByCountry(String country) throws UnirestException{
        HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/name/"+country)
                .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "0e72bcd144msh8951afd83016cbbp115df0jsnf8ca2e9da0ec")
                .asString();
        return response.getBody();
    }
}
