package fr.plopez.mydailyway;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pierr on 02/05/2017.
 */

public class ApiResultTask extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... params) {
        String resultat = null;
        HttpURLConnection connexion = null;
        try {
            URL url = new URL(params[0]);
            connexion = (HttpURLConnection)url.openConnection();


            //reception de la réponse et traitement
            InputStream is = connexion.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            // transformer le flux binaire en caractères
            int inChar;
            final StringBuilder readStr = new StringBuilder();
            while( (inChar = reader.read()) != -1 ) {
                readStr.append((char) inChar);
            }
            resultat = readStr.toString();
            connexion.disconnect();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return resultat;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}
