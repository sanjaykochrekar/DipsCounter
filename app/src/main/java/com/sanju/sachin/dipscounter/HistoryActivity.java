package com.sanju.sachin.dipscounter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sanju.sachin.dipscounter.data.DipsContract.DipsEntry;
import com.sanju.sachin.dipscounter.data.DipsDbHelper;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private DipsDbHelper mDbHelper;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("History");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        setContentView(R.layout.activity_history);

        // Constractor
        mDbHelper = new DipsDbHelper(this);

        //This method is called to display Dips history
        displayDatabaseInfo();

    }

    private void displayDatabaseInfo() {

        //ArrayList declaration
        ArrayList<details> detail;
        detail = new ArrayList<>();

        //open database to read
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //define projection that specifies which column or row from database you will actually use after this query
        String[] projection = {

                DipsEntry._ID,
                DipsEntry.COLUMN_DIPS_COUNT,
                DipsEntry.COLUMN_DATE,
                DipsEntry.COLUMN_TIME

        };

        //Perform a query on the pets table
        Cursor cursor = db.query(
                DipsEntry.TABLE_NAME,       //The table to query
                projection,                //The columns to return
                null,             //The columns for the WHERE clause
                null,          //The values for the WHERE clause
                null,              //Don't group the rows
                null,               //Don't filter by row groups
                DipsEntry._ID + " DESC");             //The sort order

        try {
            //Figure out the index of each column
            //Todo int idColumnIndex = cursor.getColumnIndex(DipsEntry._ID);
            int numberOfDipsColumnIndex = cursor.getColumnIndex(DipsEntry.COLUMN_DIPS_COUNT);
            int dateColumnIndex = cursor.getColumnIndex(DipsEntry.COLUMN_DATE);
            int timeColumnIndex = cursor.getColumnIndex(DipsEntry.COLUMN_TIME);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                //todo int currentID = cursor.getInt(idColumnIndex);
                String currentCount = cursor.getString(numberOfDipsColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentTime = cursor.getString(timeColumnIndex);

                //add values in ArrayList
                detail.add(new details(currentCount, currentDate, currentTime));
            }

            detailsAdapter adapter = new detailsAdapter(this, detail);
            ListView listView = findViewById(R.id.history_list_view);
            listView.setAdapter(adapter);


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }
}