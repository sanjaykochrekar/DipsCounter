package com.sanju.sachin.dipscounter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sanju.sachin.dipscounter.data.DipsContract.DipsEntry;

public class DipsDbHelper extends SQLiteOpenHelper {

    //database name
    private final static String DATABASE_NAME = "dips.db";

    //database version
    private final static int DATABASE_VERSION = 1;


    public DipsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + DipsEntry.TABLE_NAME + "("
                + DipsEntry._ID + " INTEGER PRIMARY KEY, "
                + DipsEntry.COLUMN_DATE + " TEXT,"
                + DipsEntry.COLUMN_DIPS_COUNT + " INTEGER)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
