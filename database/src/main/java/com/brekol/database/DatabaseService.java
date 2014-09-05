package com.brekol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-05
 */
public class DatabaseService extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 19;

    private static final String DB_NAME = "my_db_test_speed";

    // Generic column
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_VAL = "VAL";
    private static final String MY_TABLE = "MY_TABLE";

    public DatabaseService(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MY_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VAL + " INTEGER " +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MY_TABLE);
        onCreate(db);
    }


    public List<Integer> getValues() {
        List<Integer> result = new ArrayList<Integer>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + COLUMN_VAL + " FROM " + MY_TABLE, new String[]{});
        while (cursor.moveToNext()) {
            result.add(cursor.getInt(0));
        }
        cursor.close();
        database.close();
        return result;
    }

    public void save(int numerOfElements){
        SQLiteDatabase database = getWritableDatabase();
        for (int i = 0; i < numerOfElements; i++) {
            insertOneValue(database);
        }
        database.close();
    }

    private void insertOneValue(SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_VAL, 0);
        database.insert(MY_TABLE, null, contentValues);
    }


    public void clearDB() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM " + MY_TABLE);
        database.close();
    }
}
