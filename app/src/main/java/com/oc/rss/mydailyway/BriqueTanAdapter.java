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
 * Created by benet on 15/06/2017.
 */

public class BriqueTanAdapter extends ArrayAdapter<BriqueTan> {

    //tweets est la liste des models à afficher
    public BriqueTanAdapter(Context context, List<BriqueTan> briquesTan) { super(context, 0, briquesTan); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.brique_tan, parent, false);
        }

        BriqueTanAdapter.TweetViewHolder viewHolder = (BriqueTanAdapter.TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BriqueTanAdapter.TweetViewHolder();
            viewHolder.tram = (ImageView) convertView.findViewById(R.id.tram);
            viewHolder.timing = (TextView) convertView.findViewById(R.id.timing);
            viewHolder.numLigne = (ImageView) convertView.findViewById(R.id.numLigne);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Brique> briques
        BriqueTan briqueTan = getItem(position);
        Log.d("ligne de tram", briqueTan.getLigne());

        switch (briqueTan.getLigne()) {
            case "1":
                viewHolder.numLigne.setImageResource(R.drawable.t_1);
                break;
            case "2":
                viewHolder.numLigne.setImageResource(R.drawable.t_2);
                break;
            case "3":
                viewHolder.numLigne.setImageResource(R.drawable.t_3);
                break;

            default:
                viewHolder.numLigne.setImageResource(R.drawable.variable);
                break;
        }

        viewHolder.tram.setImageResource(briqueTan.getTram());
        viewHolder.timing.setText(briqueTan.getTiming());
        viewHolder.numLigne.setImageResource(R.drawable.t_2);
        viewHolder.name.setText(briqueTan.getName());

        return convertView;
    }

    private class TweetViewHolder{
        public ImageView tram;
        public TextView timing;
        public ImageView numLigne;
        public TextView name;
    }
}
