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
            URL url = new URL(params[0]+params[3]);
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


            tuileTan = new StationTan();
            JSONArray resultatJsonArray = new JSONArray(resultat);
            Log.d("json","json tan");
            for (int i = 0 ; i < resultatJsonArray.length() ; i++){
                JSONObject obj = resultatJsonArray.getJSONObject(i);
                String ligne = obj.getJSONObject("ligne").getString("numLigne");
                Log.d("ligne",ligne);
                Log.d("param",params[1]);
                if(ligne.equals(params[1])){
                    String direction = obj.getString("terminus");
                    if(direction.equals(params[2])) {
                        //String code;
                        String Nom = "COMM";
                        //String arretDestination;
                        String tempsAttente = obj.getString("temps");

                        tuileTan.setDirection(direction);
                        tuileTan.setLigne(ligne);
                        tuileTan.setTempsAttente(tempsAttente);
                        break;
                    }
                }
            }

            //-----------------------------------

            //
            url = new URL("http://open.tan.fr/ewp/arrets.json");
            //Ouverture de la connexion
            connexion = (HttpURLConnection)url.openConnection();
            //reception de la réponse et traitement
            is = connexion.getInputStream();
            reader = new InputStreamReader(is);
            //transformer le flux binaire en caractères

            final StringBuilder readStr2 = new StringBuilder();
            while( (inChar = reader.read()) != -1 ) {
                readStr2.append((char) inChar);
            }
            String resultat2 = readStr2.toString();
            //Ferme la connexion
            connexion.disconnect();



            JSONArray resultatJsonArray2 = new JSONArray(resultat2);

            for (int i = 0 ; i < resultatJsonArray2.length() ; i++){
                JSONObject obj = resultatJsonArray2.getJSONObject(i);
                String station = obj.getString("codeLieu");
                if(station.equals(params[3])){
                    String nom = obj.getString("libelle");
                        tuileTan.setNom(nom);
                        break;
                }
            }






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
