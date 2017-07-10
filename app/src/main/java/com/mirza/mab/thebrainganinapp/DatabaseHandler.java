package com.mirza.mab.thebrainganinapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by AHMED on 08-07-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bgApp";

    private static final String TABLE_SCORE = "score";

    private static final String LEVEL_NO = "level";
    private static final String LEVEL_STARS = "stars";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
                + LEVEL_NO + " INTEGER PRIMARY KEY," + LEVEL_STARS +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }


    public void addLevel(int level, int stars) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEVEL_NO, level);
        values.put(LEVEL_STARS, stars);

        database.insert(TABLE_SCORE, null, values);
        database.close();

    }

    public int getScore(int level) {
        int stars = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            String[] projection = {
                    LEVEL_STARS
            };

            String selection = LEVEL_NO + " = ?";
            String[] selectionArgs = {String.valueOf(level)};


            Cursor cursor = db.query(
                    TABLE_SCORE,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            cursor.moveToNext();
            stars = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_STARS)));
            cursor.close();
        } catch (Exception e) {
        }

        return stars;
    }

    public void updateScore(int level, int score) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEVEL_NO, level);
        values.put(LEVEL_STARS, score);

        database.update(TABLE_SCORE, values, LEVEL_NO + " = " + level, null);
        database.close();

    }

    public int getMaxLevel() {
        SQLiteDatabase db = this.getReadableDatabase();

        final SQLiteStatement stmt = db
                .compileStatement("SELECT MAX(" + LEVEL_NO + ") FROM " + TABLE_SCORE);

        return Integer.parseInt(stmt.simpleQueryForString());
    }


}
