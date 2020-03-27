package fr.android.projet_polart_masbernat;


import android.telephony.mbms.StreamingServiceInfo;

import java.util.Date;

public class NewMatch{
    String joueur1;
    String joueur2;
    String adresse;
    String date;
    String type;
    String imageLink;

    public NewMatch(){}

    public NewMatch(String j1, String j2, String ad, String d, String t, String link){
        joueur1 = j1;
        joueur2 = j2;
        adresse = ad;
        date = d;
        type = t;
        imageLink = link;
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

    @Override
    public String toString() {
        return "NewMatch{" +
                "joueur1='" + joueur1 + '\'' +
                ", joueur2='" + joueur2 + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", image='" + imageLink + '\'' +
                '}';
    }
}