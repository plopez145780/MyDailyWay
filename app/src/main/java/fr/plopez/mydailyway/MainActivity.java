package fr.plopez.mydailyway;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AsyncResponse{

    ApiResultTask asyncTask = new ApiResultTask();

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

            asyncTask.delegate = this;
            asyncTask.execute("http://api.openweathermap.org/data/2.5/weather?q=Nantes,fr&appid=61a7a58132b36300e975b4118c1ec53b");
        }
        else {
            //La connexion n'est pas disponible...
            Toast.makeText(MainActivity.this, "La connexion internet n'est pas disponible...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(Meteo output) {
        Log.d("meteo2",output.toString());
        TextView temperature = (TextView)findViewById(R.id.temperature);
        temperature.setText(output.getTemperatureRessentie()+"");
        TextView etat = (TextView)findViewById(R.id.etat);
        etat.setText(output.getEtat()+"");
        TextView vitesseVent = (TextView)findViewById(R.id.vitesseVent);
        vitesseVent.setText(output.getVitesseVent()+"");
    }
}
