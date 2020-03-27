package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    //constante
    private static final int CAMERA_REQUEST_CODE = 1;

    //propriétés
    private String photoPath = null;
    private Spinner spinner;
    private ImageView imgAffichePhoto;
    private Button btnPrendrePhoto;
    private Button valider;

    // Valeurs à récupérer
    private EditText joueur1;
    private EditText joueur2;
    private EditText adresse;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    DatabaseHelper mDatabaseHelper;
    NewMatch match;

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        spinner = (Spinner) findViewById(R.id.spinner);

        //Créer un arrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        //Spécifie le layout à utiliser
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Appliquer l'adapter au spinner
        spinner.setAdapter(adapter);

        initActivity();
    }

    /**
     * initialiation de l'activity
     */
    private void initActivity(){

        mDatabaseHelper = new DatabaseHelper(this);

        //on récupère les objetcs graphiques
        btnPrendrePhoto = (Button)findViewById(R.id.boutonPrendrePhoto);
        imgAffichePhoto = (ImageView)findViewById(R.id.image);

        joueur1 = (EditText)findViewById(R.id.nom1);
        joueur2 = (EditText) findViewById(R.id.nom2);
        adresse = (EditText) findViewById(R.id.adresse);
        mDisplayDate = (TextView) findViewById(R.id.date);

        valider = (Button)findViewById(R.id.valider);

        //méthode pour gérer les évenements
        createOnClickBtn();
    }

    /**
     * évenement clic sur le bouton pour prendre la photo
     */
    private void createOnClickBtn(){

        btnPrendrePhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendreUnePhoto();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = joueur1.getText().toString();
                String name2 = joueur2.getText().toString();
                String ad = adresse.getText().toString();
                String date = mDisplayDate.getText().toString();
                String type = spinner.getSelectedItem().toString();

                if((joueur1.length() != 0)
                        && (joueur2.length() != 0)
                        && (adresse.length() != 0)
                        && (date != "Date")
                        && (type != "Type de match")
                        && (photoPath != null)){

                    match = new NewMatch(name1, name2, ad, date, type, photoPath);
                    addData(match);

                }else{
                    toastMessage("Un champ est vide");
                }
            }
        });

        /**
         * Selectionner une date quand on clique dessus
         */
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SecondActivity.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    /**
     * Tester et executer l'insertion des infos dans la bdd
     * @param match
     */
    public void addData(NewMatch match){
        boolean insertData = mDatabaseHelper.addData(match);

        if(insertData){
            toastMessage("Data Successfuly Import");
        }else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * Notification toast customisable
     * @param message
     */
    private void toastMessage( String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    /**
     * accès à l'appareil photo et mémorise dans un fichier temporaire
     */
    private void prendreUnePhoto(){
        //créer un intent pour ouvrir une nouvelle fenêtre pour prendre la photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //tester pour controler que l'intent peut être géré
        if(intent.resolveActivity(getPackageManager()) != null) {
            //créer un nom de fichier unique
            String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time, ".jpg", photoDir);
                //enregistrer le chemin complet
                photoPath = photoFile.getAbsolutePath();
                // créer l'URI
                Uri photoUri = FileProvider.getUriForFile(SecondActivity.this,
                        SecondActivity.this.getApplicationContext().getPackageName() + ".provider",
                        photoFile);
                // transfert uri vers l'intent pour enregistrement de la photo dans un fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                //ouvrir l'activity par rapport à l'intent
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * retour de l'appel de l'appareil photo (startActivityForResult)
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //on vérifie le bon code de retour et l'état du retour
        if(requestCode == CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            // récupérer l'image
            Bitmap image = BitmapFactory.decodeFile(photoPath);
            //afficher l'image
            imgAffichePhoto.setImageBitmap(image);
        }
    }
}
