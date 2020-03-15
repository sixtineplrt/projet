package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*//english
        setAppLocale("en");
        setContentView(R.layout.activity_main);*/

        String language= "fr";
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);
    }

    //Lancer la seconde activité
    public void SecondActivity(View view){
        //Creation du intent pour la nouvelle activité (intention de créer une nouvelle activité)
        Intent intent = new Intent(this, fr.android.projet_polart_masbernat.SecondActivity.class);

        // Création de la nouvelle activité
        startActivity(intent);
    }

    //Lancer la troisième activité
    public void ThirdActivity(View view){
        //Creation du intent pour la nouvelle activité (intention de créer une nouvelle activité)
        Intent intent = new Intent(this, fr.android.projet_polart_masbernat.ThirdActivity.class);

        // Création de la nouvelle activité
        startActivity(intent);
    }

    //Lancer la quatrième activité
    public void FourthActivity(View view){
        //Creation du intent pour la nouvelle activité (intention de créer une nouvelle activité)
        Intent intent = new Intent(this, fr.android.projet_polart_masbernat.FourthActivity.class);

        // Création de la nouvelle activité
        startActivity(intent);
    }
}
