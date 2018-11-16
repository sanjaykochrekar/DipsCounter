package com.sanju.sachin.dipscounter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sanju.sachin.dipscounter.data.DipsContract.DipsEntry;
import com.sanju.sachin.dipscounter.data.DipsDbHelper;

public class DipsCounterActivity extends AppCompatActivity {
    int a = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tic_sound);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        insertDips();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.acyivity_dips_counter);


        //find the view that show dips TextView
        final TextView dipsTextView = findViewById(R.id.dips_counter);


        dipsTextView.setText("" + 0);

        dipsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();
                a = a + 1;

                dipsTextView.setText("" + a);
                //set onClickListener on that view
            }

        });
    }

    private void insertDips() {

        //create database helper
        DipsDbHelper mDbHelper = new DipsDbHelper(this);

        //Get database in write mode
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();

        //Create a contentValues object where column names are the key
        //and dips attribute from the counter
        ContentValues values = new ContentValues();
        values.put(DipsEntry.COLUMN_DIPS_COUNT, a);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = sqLiteDatabase.insert(DipsEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can displa    y a toast with the row ID.
            Toast.makeText(this, "Dips saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

}