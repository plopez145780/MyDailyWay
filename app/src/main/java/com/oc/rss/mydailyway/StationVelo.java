package com.oc.rss.mydailyway;

import android.util.Log;

/**
 * Description :
 * Version : 1.0
 * Fait par : Pierre Lopez
 * Fait le : 04/05/2017
 */

public class StationVelo extends Tuile {
    private int id;
    private String nom;
    private int placeRestante;
    private int veloDisponible;

    public StationVelo() {
    }

    public StationVelo(String nom, int placeRestante, int veloDisponible) {
        this.nom = nom;
        this.placeRestante = placeRestante;
        this.veloDisponible = veloDisponible;
    }

    public String getNom() {
        nom = nom.substring(nom.indexOf("-")+1);
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPlaceRestante() {
        return placeRestante;
    }

    public void setPlaceRestante(int placeRestante) {
        this.placeRestante = placeRestante;
    }

    public int getVeloDisponible() {
        return veloDisponible;
    }

    public void setVeloDisponible(int veloDisponible) {
        this.veloDisponible = veloDisponible;
    }

    @Override
    public String toString() {
        return "StationVelo{" +
                ", nom='" + nom + '\'' +
                ", placeRestante=" + placeRestante +
                ", veloDisponible=" + veloDisponible +
                '}';
    }
}
