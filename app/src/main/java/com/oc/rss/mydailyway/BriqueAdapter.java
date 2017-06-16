package com.oc.rss.mydailyway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by benet on 13/06/2017.
 */
public class BriqueAdapter extends ArrayAdapter<Brique> {

    //tweets est la liste des models à afficher
    public BriqueAdapter(Context context, List<Brique> briques) { super(context, 0, briques); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_brique, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.numLigne = (TextView) convertView.findViewById(R.id.numLigne);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Brique> briques
        Brique brique = getItem(position);

        viewHolder.avatar.setImageResource(brique.getColor());
        viewHolder.pseudo.setText(brique.getPseudo());
        viewHolder.numLigne.setText(brique.getNumLigne());
        viewHolder.text.setText(brique.getText());

        return convertView;
    }

    class TweetViewHolder{
        public ImageView avatar;
        public TextView pseudo;
        public TextView numLigne;
        public TextView text;
    }
}