package com.oc.rss.mydailyway;

/**
 * Description :
 * Version : 1.0
 * Fait par : Pierre Lopez
 * Fait le : 14/06/2017
 */

public class StationTan extends Tuile {
    private int id;
    private Boolean etat;
    private String tempsAttente;
    private String direction;
    private String code;
    private String Nom;
    private String arretDestination;
    private String ligne;

    @Override
    public String toString() {
        return "StationTan{" +
                "id=" + id +
                ", etat=" + etat +
                ", tempsAttente='" + tempsAttente + '\'' +
                ", direction=" + direction +
                ", code='" + code + '\'' +
                ", Nom='" + Nom + '\'' +
                ", arretDestination='" + arretDestination + '\'' +
                ", ligne='" + ligne + '\'' +
                '}';
    }

    public StationTan() {

    }

    public StationTan(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getTempsAttente() {
        return tempsAttente;
    }

    public void setTempsAttente(String tempsAttente) {
        this.tempsAttente = tempsAttente;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getArretDestination() {
        return arretDestination;
    }

    public void setArretDestination(String arretDestination) {
        this.arretDestination = arretDestination;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }
}
