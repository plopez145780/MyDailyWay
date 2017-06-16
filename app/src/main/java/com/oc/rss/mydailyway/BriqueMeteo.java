package com.oc.rss.mydailyway;

import android.content.pm.InstrumentationInfo;

/**
 * Created by benet on 14/06/2017.
 */

public class BriqueMeteo {
    private int temps;
    private String temperature;
    private int vent;
    private String vitesse;
    private String etat;

    public BriqueMeteo(int temps, String temperature, int vent, String vitesse, String etat) {
        this.temps = temps;
        this.temperature = temperature;
        this.vent = vent;
        this.vitesse = vitesse;
        this.etat = etat;
    }


    public int getTemps() {
        return temps;
    }
    public String getTemperature() {
        return temperature;
    }

    public int getVent() {
        return vent;
    }

    public String getVitesse() {
        return vitesse;
    }

    public String getEtat() {
        if (this.etat == null) return "";
        return etat;
    }
}
