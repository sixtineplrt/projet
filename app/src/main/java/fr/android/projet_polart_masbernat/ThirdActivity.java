package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private ListView mListView;
    DatabaseHelper mDatabaseHelper;
    NewMatch match;

    String j1;
    String j2;
    String adresse;
    String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mListView = (ListView)findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        // Récupérer les données et les mettre dans une liste
        Cursor data = mDatabaseHelper.getLastFiveData();

        ArrayList<NewMatch> listData = new ArrayList<>();

        while(data.moveToNext()){
            // Récupérer les valeurs dans la bdd
            j1 = data.getString(1);
            j2 = data.getString(2);
            adresse = data.getString(3);
            date = data.getString(4);

            match = new NewMatch(j1, j2, adresse, date);

            // Ajouter à l'arrylist
            listData.add(match);
        }

        // Créer la liste adapter et set l'adapter
        ListAdapter matchAdapter = new MatchAdapter(this, listData);
        mListView.setAdapter(matchAdapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
