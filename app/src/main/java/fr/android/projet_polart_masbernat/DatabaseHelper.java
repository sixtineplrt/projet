package fr.android.projet_polart_masbernat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTableLockedException;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "match_table";

    //Colonnes de la table
    private static final String COL1 = "ID";
    private static final String COL2 = "Joueur1";
    private static final String COL3 = "Joueur2";
    private static final String COL4 = "Adresse";
    private static final String COL5 = "Type";
    private static final String COL6 = "Date";
    private static final String COL7 = "Image";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, "
                + COL3 + " TEXT, "
                + COL4 + " TEXT)";
                /*+ COL5 + " DATE, "
                + COL6 + "TEXT, "
                + COL7 + "IMAGE)";*/
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /*public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else{
            return true;
        }
    }*/

    public boolean addData(NewMatch match){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, match.getJoueur1());
        contentValues.put(COL3, match.getJoueur2());
        contentValues.put(COL4, match.getAdresse());

        Log.d(TAG, "Adding data to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    /**
     * Retourne les infos de la database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
