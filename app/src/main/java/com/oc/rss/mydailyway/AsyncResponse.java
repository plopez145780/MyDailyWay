package com.oc.rss.mydailyway;

/**
 * Description :
 * Version :
 * Fait par : Pierre Lopez
 * Fait le : 03/05/2017
 */

public interface AsyncResponse {
    void processFinish(Meteo output);
    void processFinishBicloo(StationVelo output);
    void processFinishTan(StationTan output);
}
