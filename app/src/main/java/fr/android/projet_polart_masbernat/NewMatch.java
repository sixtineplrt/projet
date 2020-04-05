package fr.android.projet_polart_masbernat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewMatch{
    private String joueur1;
    private String joueur2;
    private String adresse;
    private String date;
    private String type;
    private String imageLink;
    private String latitude;
    private String longitude;

    public NewMatch(String j1, String j2, String ad, String d, String t, String link, String lati, String longi){
        joueur1 = j1;
        joueur2 = j2;
        adresse = ad;
        date = d;
        type = t;
        imageLink = link;
        latitude = lati;
        longitude = longi;
    }

    public String getJoueur1(){
        return joueur1;
    }

    public String getImageLink() { return imageLink; }

    public String getJoueur2(){
        return joueur2;
    }

    public String getAdresse(){
        return adresse;
    }

    public String getDate(){
        return date;
    }

    public String getType() { return type; }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "NewMatch{" +
                "joueur1='" + joueur1 + '\'' +
                ", joueur2='" + joueur2 + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", image='" + imageLink + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}