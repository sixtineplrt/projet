package fr.android.projet_polart_masbernat;


import java.util.Date;

public class NewMatch{
    String joueur1;
    String joueur2;
    String adresse;
    String date;

    public NewMatch(){}

    public NewMatch(String j1, String j2, String ad, String d){
        joueur1 = j1;
        joueur2 = j2;
        adresse = ad;
        date = d;
    }

    public NewMatch(String j1, String j2, String ad){
        joueur1 = j1;
        joueur2 = j2;
        adresse = ad;
    }

    public String getJoueur1(){
        return joueur1;
    }

    public String getJoueur2(){
        return joueur2;
    }

    public String getAdresse(){
        return adresse;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "NewMatch{" +
                "joueur1='" + joueur1 + '\'' +
                ", joueur2='" + joueur2 + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}