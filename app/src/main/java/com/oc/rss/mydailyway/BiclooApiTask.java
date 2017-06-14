package com.oc.rss.mydailyway;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description : Gestion des requetes vers les API de façon asynchrone
 * Version : 1.0
 * Fait par : Pierre Lopez
 * Fait le : 03/05/2017
 */

public class BiclooApiTask extends AsyncTask<String, Void, StationVelo>{

    public AsyncResponse delegate = null;

    //
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //
    @Override
    protected StationVelo doInBackground(String... params) {
        StationVelo tuileVelo = new StationVelo();
        //
        HttpURLConnection connexion;
        try {
            //
            URL url = new URL(params[0]);
            //Ouverture de la connexion
            connexion = (HttpURLConnection)url.openConnection();
            //reception de la réponse et traitement
            InputStream is = connexion.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            //transformer le flux binaire en caractères
            int inChar;
            final StringBuilder readStr = new StringBuilder();
            while( (inChar = reader.read()) != -1 ) {
                readStr.append((char) inChar);
            }
            String resultat = readStr.toString();
            //Ferme la connexion
            connexion.disconnect();

            //Parse le String en JSON
            JSONObject resultatJson = new JSONObject(resultat);

            String  nameStation = resultatJson.getString("name");
            String  availableBikeStandsStation = resultatJson.getString("available_bike_stands");
            int availableBikeStandsStationInt = Integer.parseInt(availableBikeStandsStation);
            String  availableBikesStation = resultatJson.getString("available_bikes");
            int availableBikesStationInt = Integer.parseInt(availableBikesStation);

            tuileVelo = new StationVelo(nameStation, availableBikeStandsStationInt, availableBikesStationInt);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }


        return tuileVelo;
    }


    //
    @Override
    protected void onPostExecute(StationVelo result) {
        delegate.processFinishBicloo(result);
    }

}
