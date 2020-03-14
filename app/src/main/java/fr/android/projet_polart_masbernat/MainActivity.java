package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
