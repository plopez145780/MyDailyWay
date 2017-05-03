package fr.plopez.mydailyway;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Creer un objet Trajet vide;
        ArrayList<Tuile> mesTuile = new ArrayList<>();

        Tuile meteoNantes = new Meteo(1, 20,2, "Clear");
        Log.d("meteo", meteoNantes.toString());
        mesTuile.add(meteoNantes);

        Trajet trajet = new Trajet(1,"mon1erTrajet", mesTuile);






        // Vérifier la disponibilité de la connexion internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            // La connexion est disponible. Lancer la tache asynchrone (qui met à jour l'UI)
            new ApiResultTask().execute("http://api.openweathermap.org/data/2.5/weather?q=Nantes,fr&appid=61a7a58132b36300e975b4118c1ec53b");




        }
        else {
            //La connexion n'est pas disponible...
            Toast.makeText(MainActivity.this, "La connexion internet n'est pas disponible...", Toast.LENGTH_SHORT).show();
        }
    }








    public class ApiResultTask extends AsyncTask<String, Void, String> {

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

            TextView temperature = (TextView)findViewById(R.id.temperature);
            TextView etat = (TextView)findViewById(R.id.etat);
            TextView vitesseVent = (TextView)findViewById(R.id.vitesseVent);
            //hello.setText(result);
        }

    }

}
