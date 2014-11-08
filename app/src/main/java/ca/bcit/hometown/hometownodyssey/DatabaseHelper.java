package ca.bcit.hometown.hometownodyssey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rhea on 06/10/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private int idCounter = 0;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "The Vault";
    private static final String PLAYER_TABLE_NAME = "Player";
    private static final String INVENTORY_TABLE_NAME = "Inventory";
    private static final String TREASURE_TABLE_NAME = "Treasures";
    private static final String TRADER_TABLE_NAME = "Trader";
    //Rows in table
    private static final String KEY_ID = "_id";
    //Player table rows
    private static final String KEY_MONEY = "money";
    private static final String KEY_HEAD_ITEM = "headItem";
    private static final String KEY_BODY_ITEM = "bodyItem";
    private static final String KEY_LEG_ITEM = "legItem";
    private static final String KEY_FOOT_ITEM = "footItem";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "currentDate";

    //Inventory table rows
    private static final String KEY_TYPE = "_type";
    private static final String KEY_VALUE = "_value";
    private static final String KEY_TEXT = "_text";
    private static final String KEY_IMAGE = "_image";

    //Treasure rows
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LONG = "longitude";

    //Trader Rows
    private static final String KEY_TIME = "Time";
    private static final String KEY_AVAIL = "Availability";

    private static final String PLAYER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE_NAME + " ("  +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            KEY_NAME + " TEXT NOT NULL , " +
            KEY_LEVEL + " INTEGER , " +
            KEY_MONEY + " INTEGER , " +
            KEY_DATE + " INTEGER , " +
            KEY_HEAD_ITEM + " TEXT , " +
            KEY_BODY_ITEM + " TEXT , " +
            KEY_LEG_ITEM + " TEXT , " +
            KEY_FOOT_ITEM + " TEXT );";

    private static final String INVENTORY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + INVENTORY_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_TYPE + " INTEGER NOT NULL, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_TEXT + " TEXT NOT NULL, " +
            KEY_IMAGE + " INTEGER NOT NULL, " +
            KEY_VALUE + " INTEGER );";


    private static final String TREASURE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TREASURE_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_TYPE + " INTEGER NOT NULL , " +
            KEY_LONG + " REAL  NOT NULL, " +
            KEY_LAT + " REAL NOT NULL, " +
            KEY_DATE + " INTEGER );";

    private static final String TRADER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TRADER_TABLE_NAME + "("  +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_LAT + " DOUBLE  NOT NULL, " +
            KEY_AVAIL + " INTEGER  NOT NULL, " +
            KEY_TIME + "FLOAT );";

    //Default constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    /**************************************************************
     ** PLAYER TABLE METHODS
     ***************************************************************/
    //Save player into the database
    public void savePlayerData(Player p) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + PLAYER_TABLE_NAME, null);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,  p.getPlayerName());
        values.put(KEY_LEVEL, p.getLevel());
        values.put(KEY_MONEY, p.getMoney());
        values.put(KEY_HEAD_ITEM, p.getHeadItem());
        values.put(KEY_BODY_ITEM, p.getBodyItem());
        values.put(KEY_LEG_ITEM, p.getLegItem());
        values.put(KEY_FOOT_ITEM, p.getFootItem());
        values.put(KEY_DATE, p.getDate());

       if (cursor.getCount() < 1)
       {

            db.insert(PLAYER_TABLE_NAME, null, values);
        }
        else
       {
           db.update(PLAYER_TABLE_NAME, values, KEY_ID + " = 1", null );
       }

        db.close();
    }

    public boolean playerExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        // Player Name
        cursor = db.rawQuery("SELECT " + KEY_NAME + " FROM " + PLAYER_TABLE_NAME, null);

        if (cursor.getCount() < 1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }

    }

    public void buildPlayer(Player p) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        // Player Name
        cursor = db.rawQuery("SELECT " + KEY_NAME + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getColumnCount() > 0 ) {
            cursor.moveToFirst();
            p.setPlayerName(cursor.getString(0));
        }

        // Player Level
        cursor = db.rawQuery("SELECT " + KEY_LEVEL + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0 ) {
            cursor.moveToFirst();
            p.setLevel(cursor.getInt(0));
        }

        // Player Money
        cursor = db.rawQuery("SELECT " + KEY_MONEY + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setMoney(cursor.getInt(0));
        }

        // Player Date
        cursor = db.rawQuery("SELECT " + KEY_DATE + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setDate(cursor.getInt(0));
        }

        // Player Head Item
        cursor = db.rawQuery("SELECT " + KEY_HEAD_ITEM + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setHeadItem(cursor.getString(0));
        }

        // Player Body Item
        cursor = db.rawQuery("SELECT " + KEY_BODY_ITEM + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setBodyItem(cursor.getString(0));
        }

        // Player Leg Item
        cursor = db.rawQuery("SELECT " + KEY_LEG_ITEM + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setLegItem(cursor.getString(0));
        }

        // Player Foot Item
        cursor = db.rawQuery("SELECT " + KEY_FOOT_ITEM + " FROM " + PLAYER_TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            p.setFootItem(cursor.getString(0));
        }


        db.close();
    }

    /**************************************************************
     ** TREASURE TABLE METHODS
     ***************************************************************/
    //load treasures to num rows in table
    //Add treasure
    //Delete treasure
    //get array of treasure

    //Save treasure object into database
    public void saveTreasureData(Treasure t) {
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_TYPE, t.getType());
            values.put(KEY_LAT, t.getPin().getPosition().latitude);
            values.put(KEY_LONG, t.getPin().getPosition().longitude);
            values.put(KEY_TYPE, t.getDate());

            Log.d("Adding new treasure: ", "treasure being added.");
            db.insert(TREASURE_TABLE_NAME, null, values);
        db.close();
    }

    public void buildTreasureList(ArrayList<Treasure> tList) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        int type;
        LatLng location;

        String selectQuery = "SELECT * FROM " + TREASURE_TABLE_NAME;
        cursor = db.rawQuery(selectQuery, null);

        //Iterate through all rows
        if (cursor.moveToFirst()) {
            do {
                type = cursor.getInt(1);

                location = new  LatLng(cursor.getDouble(3), cursor.getDouble (2));
                Treasure temp = new Treasure(location, type);
                temp.setDate(cursor.getInt(4));

               tList.add(temp);
            } while (cursor.moveToNext());
        }
    }

    public void deleteTreasure(Treasure t) {
        SQLiteDatabase db = this.getWritableDatabase();

        double lat = t.getPin().getPosition().latitude;
        double lon = t.getPin().getPosition().longitude;

        db.delete(TREASURE_TABLE_NAME, KEY_LAT + "=" + lat + "AND" +
                KEY_LONG + "=" + lon, null);

        db.close();
    }

    public void deleteAllTreasures() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TREASURE_TABLE_NAME, null, null);

        db.close();
    }

    /**************************************************************
     ** INVENTORY TABLE METHODS
     ***************************************************************/
    // save item
    // get all items
    // delete item
    // delete all items

    //Save item object into database
    public void saveItemData(Item i) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, i.getType());
        values.put(KEY_NAME, i.getName());
        values.put(KEY_TEXT, i.getText());
        values.put(KEY_VALUE, i.getValue());
        values.put(KEY_IMAGE, i.getImage());

        Log.d("Adding new item: ", "item being added.");
        db.insert(INVENTORY_TABLE_NAME, null, values);

        db.close();
    }

    public void buildItem(Item item, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        String selectQuery = "SELECT * FROM " + INVENTORY_TABLE_NAME + " WHERE " +
                KEY_NAME + " = \"" + name + "\"";
        cursor = db.rawQuery(selectQuery, null);

        // Construct the item
        if ( cursor.getCount() > 0 ) {
            cursor.moveToFirst();

            item.setName( cursor.getString(
                    cursor.getColumnIndex( KEY_NAME )
            ));
            item.setType( cursor.getInt(
                    cursor.getColumnIndex( KEY_TYPE )
            ));
            item.setValue( cursor.getInt(
                    cursor.getColumnIndex( KEY_VALUE )
            ));
            item.setText( cursor.getString(
                    cursor.getColumnIndex( KEY_TEXT )
            ));
            item.setImage( cursor.getInt(
                    cursor.getColumnIndex( KEY_IMAGE )
            ));
        }
    }

    public void buildInventorySection(ArrayList<Item> iList, int type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        String selectQuery = "SELECT * FROM " + INVENTORY_TABLE_NAME + " WHERE " +
                KEY_TYPE + " = " + type;

        cursor = db.rawQuery(selectQuery, null);

        //Iterate through all rows
        Log.d("Rows:" , cursor.getCount() + " ");
        if (cursor.moveToFirst()) {

            do {
                Item temp = new Item(cursor.getString(2), cursor.getString(3), cursor.getInt(1),
                        cursor.getInt(5), cursor.getInt(4));
                temp.setId(cursor.getInt(0));

                iList.add(temp);
            } while (cursor.moveToNext());
        }
    }

    public void deleteItem(Item i) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(INVENTORY_TABLE_NAME, KEY_ID + "=" + i.getId(), null);

        db.close();
    }

    public void deleteInventory() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(INVENTORY_TABLE_NAME, null, null);

        db.close();
    }


    /**************************************************************
     ** BASIC METHODS
     ***************************************************************/

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

    public static DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

}


