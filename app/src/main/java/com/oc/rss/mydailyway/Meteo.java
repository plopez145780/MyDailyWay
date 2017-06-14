package com.oc.rss.mydailyway;

/**
 * Description :
 * Version : 1.0
 * Fait par : Pierre Lopez
 * Fait le : 02/05/2017
 */

public class Meteo extends Tuile {
    //ATTRIBUTS
    private static String ville = "nantes";
    private int temperatureRessentie;
    private float vitesseVent;
    private String etat;

    //CONSTRUCTEURS
    public Meteo() {
    }
    public Meteo(int id, int temperatureRessentie, float vitesseVent, String etat) {
        this.temperatureRessentie = temperatureRessentie;
        this.vitesseVent = vitesseVent;
        this.etat = etat;
    }

    //ACCESSEURS
    //temperatureRessentie
    public int getTemperatureRessentie() {
        return temperatureRessentie  - 273;
    }
    public void setTemperatureRessentie(int temperatureRessentie) {
        this.temperatureRessentie = temperatureRessentie;
    }
    //vitesseVent
    public int getVitesseVent() {

        return (int)(vitesseVent*3.6f);
    }
    public void setVitesseVent(int vitesseVent) {
        this.vitesseVent = vitesseVent;
    }
    //etat
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Meteo{" +
                "temperatureRessentie=" + temperatureRessentie +
                ", vitesseVent=" + vitesseVent +
                ", etat='" + etat + '\'' +
                '}';
    }
}
