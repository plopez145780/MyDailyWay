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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    ApiResultTask asyncTask = new ApiResultTask();
    BiclooApiTask asyncStationBiclooTask = new BiclooApiTask();
    TanApiTask asyncStationTanTask = new TanApiTask();
    Meteo outputMeteo = new Meteo(1,1,1,"etat");
    StationVelo outputVelo = new StationVelo("etat", 1, 1);
    StationTan outputTan = new StationTan();

    private ListView mListMeteo;
    private ListView mListTan;
    private ListView mListView;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListMeteo = (ListView) findViewById(R.id.listMeteo);
        mListTan = (ListView) findViewById(R.id.listTan);
        mListView = (ListView) findViewById(R.id.listView);

        Button btn_refresh=(Button)findViewById(R.id.refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        List<BriqueMeteo> briquesMeteo = genererBriquesMeteo();
        BriqueMeteoAdapter adapterMeteo = new BriqueMeteoAdapter(MainActivity.this, briquesMeteo);
        mListMeteo.setAdapter(adapterMeteo);

        List<BriqueTan> briquesTan = genererBriquesTan();
        BriqueTanAdapter adapterTan = new BriqueTanAdapter(MainActivity.this, briquesTan);
        mListTan.setAdapter(adapterTan);

        List<Brique> briques = genererBriques();
        BriqueAdapter adapter = new BriqueAdapter(MainActivity.this, briques);
        mListView.setAdapter(adapter);
    }

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

            asyncStationTanTask.delegate = this;
            String code_station = "COMM";
            String ligne = "2";
            String direction = "Gare de Pont-Rousseau";
            String requeteStationTan = "http://open.tan.fr/ewp/tempsattente.json/";
            asyncStationTanTask.execute(requeteStationTan, ligne, direction, code_station);

        }
        else {
            //La connexion n'est pas disponible...
            Toast.makeText(MainActivity.this, "La connexion internet n'est pas disponible...",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(Meteo output) {
        outputMeteo = output;
        List<BriqueMeteo> briquesMeteo = genererBriquesMeteo();
        BriqueMeteoAdapter adapterMeteo = new BriqueMeteoAdapter(MainActivity.this, briquesMeteo);
        mListMeteo.setAdapter(adapterMeteo);
    }

    @Override
    public void processFinishBicloo(StationVelo output) {

        outputVelo = output;
        List<Brique> briques = genererBriques();
        BriqueAdapter adapter = new BriqueAdapter(MainActivity.this, briques);
        mListView.setAdapter(adapter);
    }

    @Override
    public void processFinishTan(StationTan output) {
        outputTan = output;
        List<BriqueTan> briquesTan = genererBriquesTan();
        BriqueTanAdapter adapter = new BriqueTanAdapter(MainActivity.this, briquesTan);
        mListTan.setAdapter(adapter);
    }

    private List<BriqueMeteo> genererBriquesMeteo(){

        List<BriqueMeteo> briquesMeteo = new ArrayList<BriqueMeteo>();
        briquesMeteo.add(new BriqueMeteo(R.drawable.neige,
                outputMeteo.getTemperatureRessentie() + "°C",
                R.drawable.iconevent, outputMeteo.getVitesseVent() + " Km/h", outputMeteo.getEtat()));

        return briquesMeteo;
    }

    private List<BriqueTan> genererBriquesTan(){

        List<BriqueTan> briquesTan = new ArrayList<BriqueTan>();
        briquesTan.add(new BriqueTan(R.drawable.icone_tram, outputTan.getTempsAttente(),
                R.drawable.t_2, outputTan.getNom(), outputTan.getLigne()));

        return briquesTan;
    }

    private List<Brique> genererBriques(){

        List<Brique> briques = new ArrayList<Brique>();
        briques.add(new Brique(R.drawable.icone_velo, outputVelo.getVeloDisponible()+" vélos", " ", outputVelo.getNom()));
        briques.add(new Brique(R.drawable.icone_place, outputVelo.getPlaceRestante()+" places", " ", outputVelo.getNom()));
        return briques;
    }
}
