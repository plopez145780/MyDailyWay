package com.oc.rss.mydailyway;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by benet on 14/06/2017.
 */

public class BriqueMeteoAdapter extends ArrayAdapter<BriqueMeteo> {

    public String etat;

    //tweets est la liste des models à afficher
    public BriqueMeteoAdapter(Context context, List<BriqueMeteo> briquesMeteo) { super(context, 0, briquesMeteo); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.brique_meteo, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.temps = (ImageView) convertView.findViewById(R.id.temps);
            viewHolder.temperature = (TextView) convertView.findViewById(R.id.temperature);
            viewHolder.vent = (ImageView) convertView.findViewById(R.id.vent);
            viewHolder.vitesse = (TextView) convertView.findViewById(R.id.vitesse);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Brique> briques
        BriqueMeteo briqueMeteo = getItem(position);


        switch (briqueMeteo.getEtat()) {
            case "01d":
                viewHolder.temps.setImageResource(R.drawable.soleil);
                break;
            case "02d":
                viewHolder.temps.setImageResource(R.drawable.couvert);
                break;
            case "03d":
                viewHolder.temps.setImageResource(R.drawable.tres_couvert);
                break;
            case "04d":
                viewHolder.temps.setImageResource(R.drawable.nuageux);
                break;
            case "09d":
                viewHolder.temps.setImageResource(R.drawable.pluie);
                break;
            case "10d":
                viewHolder.temps.setImageResource(R.drawable.variable);
                break;
            case "11d":
                viewHolder.temps.setImageResource(R.drawable.orage);
                break;
            case "13d":
                viewHolder.temps.setImageResource(R.drawable.neige);
                break;
            case "50d":
                viewHolder.temps.setImageResource(R.drawable.brouillard);
                break;

            default:
                viewHolder.temps.setImageResource(R.drawable.variable);
                break;
        }

        viewHolder.temperature.setText(briqueMeteo.getTemperature()+"");
        viewHolder.vent.setImageResource(briqueMeteo.getVent());
        viewHolder.vitesse.setText(briqueMeteo.getVitesse()+"");

        return convertView;
    }

    private class TweetViewHolder{
        public ImageView temps;
        public TextView temperature;
        public ImageView vent;
        public TextView vitesse;
    }
}