package edu.gcu.betterweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CacheDatabase extends SQLiteOpenHelper {

    public static final String USER_DATA_TABLE = "USER_DATA";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_CURRENT_CITY = "USER_CURRENT_CITY";
    public static final String COLUMN_USER_ALT_CITY_1 = "USER_ALT_CITY_1";
    public static final String COLUMN_USER_ALT_CITY_2 = "USER_ALT_CITY_2";
    private static final String COLUMN_ID = "ID";

    public CacheDatabase(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_DATA_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_CURRENT_CITY + " TEXT, " +
                COLUMN_USER_ALT_CITY_1 + " TEXT, " + COLUMN_USER_ALT_CITY_2 + " TEXT)";
        
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userData.getfName());
        cv.put(COLUMN_USER_EMAIL, userData.getEmail());
        cv.put(COLUMN_USER_CURRENT_CITY, userData.getCurrentLocation());
        cv.put(COLUMN_USER_ALT_CITY_1, userData.getAltCity1());
        cv.put(COLUMN_USER_ALT_CITY_2, userData.getAltCity2());

        long insert = db.insert(USER_DATA_TABLE, null, cv);
        if (insert == -1)
            return false;
        else
            return true;
    }
}
