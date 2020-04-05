package fr.android.projet_polart_masbernat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTableLockedException;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DABASE_NAME = "myMatches";
    private static final String TABLE_NAME = "match_table";
    private SQLiteDatabase db;

    //Colonnes de la table
    private static final String COL1 = "ID";
    private static final String COL2 = "Joueur1";
    private static final String COL3 = "Joueur2";
    private static final String COL4 = "Adresse";
    private static final String COL5 = "Date";
    private static final String COL6 = "Type";
    private static final String COL7 = "Image";
    private static final String COL8 = "Latitude";
    private static final String COL9 = "Longitude";

    public DatabaseHelper(Context context){
        super(context, DABASE_NAME, null ,1);
        db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        try {
            String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL2 + " TEXT, "
                    + COL3 + " TEXT, "
                    + COL4 + " TEXT, "
                    + COL5 + " TEXT, "
                    + COL6 + " TEXT, "
                    + COL7 + " TEXT, "
                    + COL8 + " TEXT, "
                    + COL9 + " TEXT) ";
            db.execSQL(createTable);
        } catch (Exception e) {
            // do nothing
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Add data à la bdd locale
     * @param match
     * @return
     */
    public long addData(NewMatch match){
        //Log.d(TAG, db.toString());
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, match.getJoueur1());
        contentValues.put(COL3, match.getJoueur2());
        contentValues.put(COL4, match.getAdresse());
        contentValues.put(COL5, match.getDate());
        contentValues.put(COL6, match.getType());
        contentValues.put(COL7, match.getImageLink());
        contentValues.put(COL8, match.getLatitude());
        contentValues.put(COL9, match.getLongitude());

        Log.d(TAG, "Adding data to " + TABLE_NAME);

        return db.insert(TABLE_NAME, null, contentValues);
    }

    public void addDataFirebase(NewMatch match, DatabaseReference mReference){
        //mReference.child("NewMatch").push();
        mReference = mReference.child("NewMatch");

        mReference.child("Joueur1").push().setValue(match.getJoueur1());
        mReference.child("Joueur2").push().setValue(match.getJoueur2());
        mReference.child("Adresse").push().setValue(match.getAdresse());
        mReference.child("Type").push().setValue(match.getType());
        mReference.child("Date").push().setValue(match.getDate());
        mReference.child("Image").push().setValue(match.getImageLink());
        mReference.child("Latitude").push().setValue(match.getLatitude());
        mReference.child("Longitude").push().setValue(match.getLongitude());
    }

    /**
     * Retourne les 5 derniers matchs de la database
     * @return
     */
    public Cursor getLastFiveData(){
        String query = "select * from " + TABLE_NAME + " order by ID desc limit 5";
        return db.rawQuery(query, null);
    }

    /**
     * Afficher la bdd
     * @return
     */
    // TODO Remove before prod
    public String getTableAsString() {
        Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", TABLE_NAME);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        return tableString;
    }
}
