package com.oc.rss.mydailyway;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
// Début d'ajout
    ApiResultTask asyncTask = new ApiResultTask();
    BiclooApiTask asyncStationBiclooTask = new BiclooApiTask();
    Meteo outputMeteo = new Meteo(1,1,1,"etat");
    StationVelo outputVelo = new StationVelo("etat", 1, 1);
// Fin d'ajout

    private ListView mListView;
    private ListView mListMeteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mListMeteo = (ListView) findViewById(R.id.listMeteo);

        List<BriqueMeteo> briquesMeteo = genererBriquesMeteo();
        BriqueMeteoAdapter adapterMeteo = new BriqueMeteoAdapter(MainActivity.this, briquesMeteo);
        mListMeteo.setAdapter(adapterMeteo);

        List<Brique> briques = genererBriques();
        BriqueAdapter adapter = new BriqueAdapter(MainActivity.this, briques);
        mListView.setAdapter(adapter);
    }

    //  Début d'insertion
    @Override
    protected void onStart() {
        super.onStart();

        //Creer un objet Trajet vide;
        ArrayList<Tuile> mesTuile = new ArrayList<>();
        Tuile meteoNantes = new Meteo(1, 20,2, "Clear");
        mesTuile.add(meteoNantes);
        Trajet trajet = new Trajet(1,"mon1erTrajet", mesTuile);


        // Vérifier la disponibilité de la connexion internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            // La connexion est disponible. Lancer la tache asynchrone (qui met à jour l'UI)

            asyncTask.delegate = this;
            asyncTask.execute("http://api.openweathermap.org/data/2.5/weather?q=Nantes,fr&appid=61a7a58132b36300e975b4118c1ec53b");

            asyncStationBiclooTask.delegate = this;
            int station_number = 56;
            String requeteStationBicloo = "https://api.jcdecaux.com/vls/v1/stations/"+station_number+"?contract=Nantes&apiKey=5b45ad73afd612e6553401c6ac3d33f276da8dfa";
            asyncStationBiclooTask.execute(requeteStationBicloo);

        }
        else {
            //La connexion n'est pas disponible...
            Toast.makeText(MainActivity.this, "La connexion internet n'est pas disponible...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(Meteo output) {
        outputMeteo = output;
        //Log.d("meteo",outputMeteo.toString());
        List<BriqueMeteo> briquesMeteo = genererBriquesMeteo();
        BriqueMeteoAdapter adapterMeteo = new BriqueMeteoAdapter(MainActivity.this, briquesMeteo);
        mListMeteo.setAdapter(adapterMeteo);
    }

    @Override
    public void processFinishBicloo(StationVelo output) {

       //Log.d("STATION BICLOO",output.toString());
        outputVelo = output;
        List<Brique> briques = genererBriques();
        BriqueAdapter adapter = new BriqueAdapter(MainActivity.this, briques);
        mListView.setAdapter(adapter);
    }
// Fin d'insertion

    private List<BriqueMeteo> genererBriquesMeteo(){

        List<BriqueMeteo> briquesMeteo = new ArrayList<BriqueMeteo>();
        briquesMeteo.add(new BriqueMeteo(BriqueMeteoAdapter.tab_image2[1], outputMeteo.getTemperatureRessentie()+"°C", BriqueMeteoAdapter.tab_image2[2], outputMeteo.getVitesseVent()+" Km/h"));
        return briquesMeteo;
    }

    private List<Brique> genererBriques(){

        List<Brique> briques = new ArrayList<Brique>();
        //briques.add(new Brique(BriqueAdapter.tab_image[2], outputMeteo.getTemperatureRessentie()+"", outputMeteo.getVitesseVent()+""));
        briques.add(new Brique(BriqueAdapter.tab_image[0], "La Tan", " ", "Pas de retard"));
        briques.add(new Brique(BriqueAdapter.tab_image[2], outputVelo.getVeloDisponible()+" vélos", " ", outputVelo.getNom()));
        briques.add(new Brique(BriqueAdapter.tab_image[1], outputVelo.getPlaceRestante()+" places", " ", outputVelo.getNom()));
        return briques;
    }
}
