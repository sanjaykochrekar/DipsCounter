package com.sanju.sachin.dipscounter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sanju.sachin.dipscounter.data.DipsContract.DipsEntry;
import com.sanju.sachin.dipscounter.data.DipsDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DipsCounterActivity extends AppCompatActivity {
    //"dipsCount" is variable that keeps track of number of dips
    int dipsCount = 0;


    // MediaPlayer that makes tic sound on clicking DipsCounter TextView
    private MediaPlayer mediaPlayer;

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tic_sound);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide status bar and ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.acyivity_dips_counter);


        //find the view that show dips TextView
        final TextView dipsTextView = findViewById(R.id.dips_counter);
        dipsTextView.setText("" + 0);

        //set onclickListener on DipsCounter TextView
        dipsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();
                dipsCount++;

                dipsTextView.setText("" + dipsCount);
            }

        });
    }

    private void insertDips() {

        String d = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        //create database helper
        DipsDbHelper mDbHelper = new DipsDbHelper(this);

        //Get database in write mode
        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();

        //Create a contentValues object where column names are the key
        //and dips attribute from the counter
        ContentValues values = new ContentValues();
        values.put(DipsEntry.COLUMN_DIPS_COUNT, dipsCount);
        values.put(DipsEntry.COLUMN_DATE, d);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = sqLiteDatabase.insert(DipsEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Dips saved with row id: " + newRowId + "  " + d, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        //If dips is we need not to insert
        if (dipsCount != 0) {
            insertDips();
        }
        //Stop media player
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        Toast.makeText(this, "Total dips " + dipsCount, Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

}