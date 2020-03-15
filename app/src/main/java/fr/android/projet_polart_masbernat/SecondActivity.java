package fr.android.projet_polart_masbernat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    //constante
    private static final int CAMERA_REQUEST_CODE = 1;

    //propriétés
    private String photoPath = null;
    private Spinner spinner;
    private ImageView imgAffichePhoto;
    private Button btnPrendrePhoto;

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
        //on récupère les objetcs graphiques
        btnPrendrePhoto = (Button)findViewById(R.id.boutonPrendrePhoto);
        imgAffichePhoto = (ImageView)findViewById(R.id.image);

        //méthode pour gérer les évenements
        createOnClickBtnPrendrePhoto();
    }

    /**
     * évenement clic sur le bouton pour prendre la photo
     */
    private void createOnClickBtnPrendrePhoto(){
        btnPrendrePhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendreUnePhoto();
            }
        });
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
