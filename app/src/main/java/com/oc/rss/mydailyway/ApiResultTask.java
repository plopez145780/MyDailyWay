package com.oc.rss.mydailyway;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
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
 * Fait le : 02/05/2017
 */

public class ApiResultTask extends AsyncTask<String, Void, Meteo>{

    public AsyncResponse delegate = null;

    //
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //
    @Override
    protected Meteo doInBackground(String... params) {
        Meteo tuileMeteo = new Meteo();
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

            Log.d("TRUC", "truc");

            //Parse le String en JSON
            JSONObject resultatJson = new JSONObject(resultat);
            //Récupère le code icon du temp
            JSONArray weatherArray = resultatJson.getJSONArray("weather");
            JSONObject  weatherObj = weatherArray.getJSONObject(0);
            String  weatherIcon = weatherObj.getString("icon");
            Log.d("WEATHER", weatherIcon);

            //Texte de la température
            JSONObject  mainObj = resultatJson.getJSONObject("main");
            String  mainTemp = mainObj.getString("temp");
            float mainTempInt = Float.parseFloat(mainTemp);
            //Texte de la vitesse du vent
            JSONObject  windObj = resultatJson.getJSONObject("wind");
            String  windSpeed = windObj.getString("speed");
            float windSpeedInt = Float.parseFloat(windSpeed);

            tuileMeteo = new Meteo(2, (int)mainTempInt, windSpeedInt, weatherIcon);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }


        return tuileMeteo;
    }


    //
    @Override
    protected void onPostExecute(Meteo result) {
        delegate.processFinish(result);
    }

}
