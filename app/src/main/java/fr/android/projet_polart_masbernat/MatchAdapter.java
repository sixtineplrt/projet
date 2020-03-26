package fr.android.projet_polart_masbernat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MatchAdapter extends ArrayAdapter<NewMatch> {

    public MatchAdapter(Context context, ArrayList<NewMatch> listData){
        super(context, 0, listData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        NewMatch match = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.match, parent, false);
        }

        TextView joueur1 = (TextView) convertView.findViewById(R.id.joueur1);
        TextView joueur2 = (TextView) convertView.findViewById(R.id.joueur2);
        TextView adresse = (TextView) convertView.findViewById(R.id.adresse);

        joueur1.setText(match.joueur1);
        joueur2.setText(match.joueur2);
        adresse.setText(match.adresse);

        return convertView;
    }
}
