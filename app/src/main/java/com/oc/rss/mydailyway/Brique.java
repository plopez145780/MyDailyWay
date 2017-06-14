package com.oc.rss.mydailyway;

/**
 * Created by benet on 13/06/2017.
 */
public class Brique {
    private int color;
    private String pseudo;
    private String numLigne;
    private String text;

    public Brique(int color, String pseudo, String numLigne, String text) {
        this.color = color;
        this.pseudo = pseudo;
        this.numLigne = numLigne;
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public String getPseudo() {
        return pseudo;
    }


    public String getNumLigne() {
        return numLigne;
    }

    public String getText() {
        return text;
    }
}
