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
    public static Integer[] tab_image2 = {
            R.drawable.pluie,
            R.drawable.soleil,
            R.drawable.iconevent };

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

        /*if (position == 0)
            viewHolder.temps.setImageResource(tab_image[1]);
        else
            viewHolder.temps.setImageResource(tab_image[position]);*/
        Log.d("brique meteo", briqueMeteo.toString());

        viewHolder.temps.setImageResource(briqueMeteo.getTemps());
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

