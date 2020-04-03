package fr.android.projet_polart_masbernat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

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
        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView latitude = (TextView) convertView.findViewById(R.id.latitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.longitude);

        joueur1.setText(match.joueur1);
        joueur2.setText(match.joueur2);
        adresse.setText(match.adresse);
        dateView.setText(match.date);
        type.setText(match.type);
        latitude.setText(match.latitude);
        longitude.setText(match.longitude);

        Bitmap imageBitmap = BitmapFactory.decodeFile(match.imageLink);
        image.setImageBitmap(imageBitmap);

        return convertView;
    }
}
