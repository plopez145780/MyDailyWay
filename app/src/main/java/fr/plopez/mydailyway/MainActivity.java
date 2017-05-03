package fr.plopez.mydailyway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    TextView temperature = (TextView)findViewById(R.id.temperature);
    TextView etat = (TextView)findViewById(R.id.etat);
    TextView vitesseVent = (TextView)findViewById(R.id.vitesseVent);

    public void afficheTemperature(View view) {
        temperature.setText("Temperature");
    }

    public void afficheEtat(View view) {
        etat.setText("Etat");
    }

    public void afficheVitesse(View view) {
        vitesseVent.setText("Vitesse");
    }
}
