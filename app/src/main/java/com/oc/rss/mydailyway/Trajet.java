package com.oc.rss.mydailyway;

import java.util.ArrayList;

/**
 * Description :
 * Version :
 * Fait par : Pierre Lopez
 * Le 02/05/2017
 */

public class Trajet {
    //ATTRIBUTS DE CLASSE
    private int id;
    private String nom;
    private ArrayList<Tuile> listeTuile = new ArrayList<>();

    //CONSTRUCTEURS
    public Trajet() {
    }
    public Trajet(int id, String nom, ArrayList<Tuile> listeTuile) {
        this.id = id;
        this.nom = nom;
        this.listeTuile = listeTuile;
    }

    //ACCESSEURS
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //Nom
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    //ListeTuile
    public ArrayList<Tuile> getListeTuile() {
        return listeTuile;
    }
    public void setListeTuile(ArrayList<Tuile> listeTuile) {
        this.listeTuile = listeTuile;
    }
}
