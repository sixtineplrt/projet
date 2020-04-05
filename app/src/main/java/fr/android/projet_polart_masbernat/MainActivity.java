package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button sauvegarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String locale = new String(Locale.getDefault().getDisplayLanguage());

        changeLocale(this.getResources(), locale);

        setContentView(R.layout.activity_main);

        sauvegarde = (Button)findViewById(R.id.save);
        sauvegarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fr.android.projet_polart_masbernat.ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Gestion de la langue
     * @param res
     * @param locale
     */
    public static void changeLocale(Resources res, String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale) {
            case "fr":
                config.locale = Locale.FRANCE;
                break;
            case "en":
                config.locale = Locale.ENGLISH;
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
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
}
