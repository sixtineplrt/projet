package fr.android.projet_polart_masbernat;


public class NewMatch{
    String joueur1;
    String joueur2;
    String adresse;

    public NewMatch(){}

    public NewMatch(String j1, String j2, String ad){
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.adresse = ad;
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
}