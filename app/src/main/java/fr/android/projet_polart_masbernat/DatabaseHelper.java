package fr.android.projet_polart_masbernat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTableLockedException;
import android.util.Log;

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

    public DatabaseHelper(Context context){
        super(context, DABASE_NAME, null ,1);
        db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL2 + " TEXT, "
                    + COL3 + " TEXT, "
                    + COL4 + " TEXT, "
                    + COL5 + " TEXT)";
            //+ COL6 + " TEXT) ";
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
     * Add data à la bdd
     * @param match
     * @return
     */
    public boolean addData(NewMatch match){
        Log.d(TAG, db.toString());
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, match.getJoueur1());
        contentValues.put(COL3, match.getJoueur2());
        contentValues.put(COL4, match.getAdresse());
        contentValues.put(COL5, match.getDate());

        Log.d(TAG, "Adding data to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        Log.d(TAG, this.getTableAsString());

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
