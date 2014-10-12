package ca.bcit.hometown.hometownodyssey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rhea on 06/10/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String STORAGE_TABLE_NAME = "VALUES";
    private static final String DATABASE_NAME = "The Vault";
    //Rows in table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private static final String STORAGE_TABLE_CREATE= "CREATE TABLE VALUES(_id TEXT PRIMARY  KEY NOT NULL, name TEXT NOT NULL);";

    //Default constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STORAGE_TABLE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS VALUES");

        // Create tables again
        onCreate(database);
    }

    public void addValue(String ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, ID);
        values.put(KEY_NAME, name);

        db.insert(STORAGE_TABLE_NAME, null, values);
        db.close();
    }

    public String getValue(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        cursor = db.query(STORAGE_TABLE_NAME, new String[]{KEY_ID, KEY_NAME}, ID + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(0);

    }

    public List<String> getAllValues() {
        List<String> valueList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + STORAGE_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Iterate through all rows
        if (cursor.moveToFirst()) {
            do {
                valueList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return valueList;
    }

    public int getValueCount() {
        String countQuery = "SELECT  * FROM " + STORAGE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteValue(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STORAGE_TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(ID)});
        db.close();
    }

}


