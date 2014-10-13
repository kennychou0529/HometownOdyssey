package ca.bcit.hometown.hometownodyssey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rhea on 06/10/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "The Vault";
    private static final String PLAYER_TABLE_NAME = "Player";
    private static final String INVENTORY_TABLE_NAME = "Inventory";
    private static final String TREASURE_TABLE_NAME = "Treasures";
    private static final String TRADER_TABLE_NAME = "Trader";
    //Rows in table
    private static final String KEY_ID = "id";
    //Player table rows
    private static final String KEY_MONEY = "money";
    private static final String KEY_HEAD_ITEM = "headItem";
    private static final String KEY_BODY_ITEM = "bodyItem";
    private static final String KEY_LEG_ITEM = "legItem";
    private static final String KEY_FOOT_ITEM = "footItem";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAME = "name";

    //Inventory table rows
    private static final String KEY_TYPE = "type";
    private static final String KEY_VALUE = "value";

    //Treasure rows
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LONG = "longitude";

    //Trader Rows
    private static final String KEY_TIME = "Time";
    private static final String KEY_AVAIL = "Availability";

    private static final String PLAYER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_LEVEL + " INTEGER, " +
            KEY_MONEY + " INTEGER, " +
            KEY_HEAD_ITEM + " INTEGER, " +
            KEY_BODY_ITEM + " INTEGER, " +
            KEY_LEG_ITEM + " INTEGER, " +
            KEY_FOOT_ITEM + " INTEGER);";

    private static final String INVENTORY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + INVENTORY_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_TYPE + "INTEGER NOT NULL, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_VALUE + "INTEGER, " +
            KEY_LEG_ITEM + "TEXT );";


    private static final String TREASURE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TREASURE_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_TYPE + "INTEGER NOT NULL ," +
            KEY_LONG + "FLOAT  NOT NULL, " +
            KEY_LAT + "FLOAT );";

    private static final String TRADER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TRADER_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_LAT + "FLOAT  NOT NULL, " +
            KEY_AVAIL + "INTEGER  NOT NULL, " +
            KEY_TIME + "FLOAT );";

    //Default constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("FUCKING CHRIST", "X");
        db.execSQL(PLAYER_TABLE_CREATE);
        db.execSQL(INVENTORY_TABLE_CREATE);
        db.execSQL(TREASURE_TABLE_CREATE);
        db.execSQL(TRADER_TABLE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + TREASURE_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + TRADER_TABLE_NAME);

        // Create tables again
        onCreate(database);
    }

    public void savePlayerData(Player p) {
        SQLiteDatabase db = this.getWritableDatabase();

        String pName = p.getPlayerName();
        Cursor cursor = db.rawQuery("SELECT * from " + PLAYER_TABLE_NAME, null);

       //+ " WHERE " + KEY_NAME  + " = \"" + pName + "\""

       if (cursor.getCount() < 1)
       {
          //TODO: ADD CORRECT VALUES FOR INVENTORY
           ContentValues values = new ContentValues();
           values.put(KEY_NAME, pName);
           values.put(KEY_LEVEL, p.getLevel());
           values.put(KEY_MONEY, 100000);
           values.put(KEY_HEAD_ITEM, 0);
           values.put(KEY_BODY_ITEM, 0);
           values.put(KEY_LEG_ITEM, 0);
           values.put(KEY_FOOT_ITEM, 0);

           Log.d("Adding new player: ", "Player being added.");
           db.insert(PLAYER_TABLE_NAME, null, values);
       }

        db.close();
    }

    public void buildPlayer(Player p) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        // Player Name
        cursor = db.rawQuery("SELECT " + KEY_NAME + " FROM " + PLAYER_TABLE_NAME, null);
        p.setPlayerName(cursor.getString(0));

        // Player Name
        cursor = db.rawQuery("SELECT " + KEY_LEVEL + " FROM " + PLAYER_TABLE_NAME, null);
        p.setLevel(cursor.getInt(0));

        // Player Name
        cursor = db.rawQuery("SELECT " + KEY_MONEY + " FROM " + PLAYER_TABLE_NAME, null);
        p.setMoney(cursor.getInt(0));

        // TODO: Player equipment loading
    }

    public String getValue(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        cursor = db.query(PLAYER_TABLE_NAME, new String[]{KEY_ID, KEY_NAME}, ID + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(0);

    }

    public List<String> getAllValues() {
        List<String> valueList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + PLAYER_TABLE_NAME;

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
        String countQuery = "SELECT  * FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteValue(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PLAYER_TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(ID)});
        db.close();
    }

}


