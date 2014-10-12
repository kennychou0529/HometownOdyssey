//package ca.bcit.hometown.hometownodyssey;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
///**
// * Created by Rhea on 06/10/2014.
// */
//public class Database{
//
//    private DatabaseHelper dbHelper;
//
//    private SQLiteDatabase database;
//
//    public String TABLE_NAME; // name of table
//
//    public final static String ID ="id"; // id value
//    public final static String NAME="name";
//
//    /**
//     *
//     * @param context
//     */
//    public Database(Context context, String tableName ){
//        dbHelper = new DatabaseHelper(context);
//        TABLE_NAME = tableName;
//        database = dbHelper.getWritableDatabase();
//    }
//
//
//    public long createRecords(String id, String name){
//        ContentValues values = new ContentValues();
//        values.put(ID, id);
//        values.put(NAME, name);
//        return database.insert(TABLE_NAME, null, values);
//    }
//
//    public long deleteRecords(String id, String name) {
//       // TODO: How do i delete?!?!
//        return 0;
//    }
//
//    public Cursor selectRecords() {
//        String[] cols = new String[]{ID, NAME};
//        Cursor mCursor = database.query(true, TABLE_NAME, cols, null
//                , null, null, null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
//        return mCursor; // iterate to get each value.
//    }
//}