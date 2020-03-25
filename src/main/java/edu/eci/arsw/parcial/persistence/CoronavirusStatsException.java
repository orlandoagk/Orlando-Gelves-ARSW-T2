package edu.eci.arsw.parcial.persistence;

public class CoronavirusStatsException extends Exception {
    public final static String FALLO_EN_REQUEST = "Unirest no pudó realizar el request que se intento hacer";
    public CoronavirusStatsException(String ex){
        super(ex);
    }
}
