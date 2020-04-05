package fr.android.projet_polart_masbernat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private ListView mListView;
    private DatabaseHelper mDatabaseHelper;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private NewMatch match;
    private Context context = this;

    private String j1;
    private String j2;
    private String adresse;
    private String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private String type;
    private String image;
    private String latitude;
    private String longitude;
    private Button rechercher;
    private Button fiveLastMatches;
    private EditText recherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // On récupère les objets graphiques
        mListView = (ListView)findViewById(R.id.listView);
        rechercher = (Button)findViewById(R.id.rechercher);
        fiveLastMatches = (Button)findViewById(R.id.fiveLast);
        recherche = (EditText)findViewById(R.id.recherche);
        // BDD locale
        mDatabaseHelper = new DatabaseHelper(this);
        // BBD externe
        database = FirebaseDatabase.getInstance();

        OnClickButton();
    }

    private void OnClickButton(){
        rechercher.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                mReference = database.getReference("Matches");
                //final List<NewMatch> matches = new ArrayList<>();
                final ArrayList<NewMatch> listData = new ArrayList<>();

                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,Object> tmp = (Map<String,Object>) dataSnapshot.getValue();
                        for (Map.Entry<String,Object> e : tmp.entrySet()) {
                            Map<String,String> m = (Map<String, String>) e.getValue();

                            String index =  m.get("joueur1") +
                                    m.get("joueur2") +
                                    m.get("adresse") +
                                    m.get("date") +
                                    m.get("type") +
                                    m.get("latitude") +
                                    m.get("longitude");

                            String ref = recherche.getText().toString();
                            if (index.contains(ref)) {
                                /*matches.add(new NewMatch(m.get("joueur1"),
                                        m.get("joueur2"),
                                        m.get("adresse"),
                                        m.get("date"),
                                        m.get("type"),
                                        m.get("imageLink"),
                                        m.get("latitude"),
                                        m.get("longitude")
                                ));*/
                                listData.add(new NewMatch(m.get("joueur1"),
                                        m.get("joueur2"),
                                        m.get("adresse"),
                                        m.get("date"),
                                        m.get("type"),
                                        m.get("imageLink"),
                                        m.get("latitude"),
                                        m.get("longitude")
                                ));
                            }
                        }
                        //Log.d(TAG, "matches = " + matches.size() + " " + matches);
                        Log.d(TAG, "matches = " + listData.size() + " " + listData);

                        // Créer la liste adapter et set l'adapter
                        ListAdapter matchAdapter = new MatchAdapter(context , listData);
                        mListView.setAdapter(matchAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "The read failed: " + databaseError.getCode());
                    }
                });
            }
        });

        fiveLastMatches.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                populateListView();
            }
        });
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
            type = data.getString(5);
            image = data.getString(6);
            latitude = data.getString(7);
            longitude = data.getString(8);

            match = new NewMatch(j1, j2, adresse, date, type, image, latitude, longitude);

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
