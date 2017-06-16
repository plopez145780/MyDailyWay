package com.oc.rss.mydailyway;

/**
 * Created by benet on 15/06/2017.
 */

public class BriqueTan {
    private int tram;
    private String timing;
    private int numLigne;
    private String name;
    private String ligne="";

    public BriqueTan(int tram, String timing, int numLigne, String name, String ligne) {
        this.tram = tram;
        this.timing = timing;
        this.numLigne = numLigne;
        this.name = name;
        this.ligne = ligne;
    }


    public int getTram() {
        return tram;
    }
    public String getTiming() {
        return timing;
    }
    public int getNumLigne() {
        return numLigne;
    }
    public String getName() {
        return name;
    }
    public String getLigne() {
        if (this.ligne == null) return "";
        return ligne;
    }
}

