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
 * Fait le : 14/06/2017
 */

public class TanApiTask extends AsyncTask<String, Void, StationTan>{

    public AsyncResponse delegate = null;

    //
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //
    @Override
    protected StationTan doInBackground(String... params) {
        StationTan tuileTan = new StationTan();
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



            //TODO faire correspondre les info du flux au variable de l'objet
            String tempsAttente;
            String direction;
            String code;
            String Nom;
            String arretDestination;
            String ligne;


            /*String  nameStation = resultatJson.getString("name");
            String  availableBikeStandsStation = resultatJson.getString("available_bike_stands");
            int availableBikeStandsStationInt = Integer.parseInt(availableBikeStandsStation);
            String  availableBikesStation = resultatJson.getString("available_bikes");
            int availableBikesStationInt = Integer.parseInt(availableBikesStation);*/

            tuileTan = new StationTan();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }
        return tuileTan;
    }

    //
    @Override
    protected void onPostExecute(StationTan result) {
        delegate.processFinishTan(result);
    }

}
