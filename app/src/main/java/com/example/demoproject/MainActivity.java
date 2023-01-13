package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button watchlistButton = (Button) findViewById(R.id.watchlist_button);
        Button databaseButton = (Button) findViewById(R.id.database_button);

        watchlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MainActivity.this,WatchlistActivity.class);
                startActivity(intent);
            }
        });

        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (MainActivity.this,DataBaseActivity.class);
                startActivity(intent);
            }
        });

    }
}