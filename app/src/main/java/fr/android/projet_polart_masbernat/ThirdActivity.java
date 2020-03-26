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
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private ListView mListView;
    DatabaseHelper mDatabaseHelper;
    NewMatch match;

    String j1;
    String j2;
    String adresse;

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
        Cursor data = mDatabaseHelper.getData();
        ArrayList<NewMatch> listData = new ArrayList<NewMatch>();
        ArrayList<String> listTest = new ArrayList<>();

        while(data.moveToNext()){
            // Récupérer les valeurs dans la bdd
            j1 = data.getString(1);
            j2 = data.getString(2);
            adresse = data.getString(3);

            match = new NewMatch(j1, j2, adresse);

            // Ajouter à l'arrylist
            listData.add(match);
            listTest.add(j1);
        }
        // Créer la liste adapter et set l'adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTest);
        ListAdapter matchAdapter = new MatchAdapter(this, listData);
        mListView.setAdapter(matchAdapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
