package fr.plopez.mydailyway;

import java.util.ArrayList;

/**
 * Created by pierr on 02/05/2017.
 */

public class Trajet {
    private int id;
    private ArrayList<Tuile> listeTuile = new ArrayList<Tuile>();
    private String nom;

    public Trajet() {
    }

    public Trajet(int id, ArrayList<Tuile> listeTuile, String nom) {
        this.id = id;
        this.listeTuile = listeTuile;
        this.nom = nom;
    }
}
