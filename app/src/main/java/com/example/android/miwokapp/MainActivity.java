package com.example.android.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.miwok.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView number = (TextView) findViewById(R.id.numbers);

        number.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent number = new Intent(MainActivity.this, NumbersActivity.class);
                // Start the new activity
                startActivity(number);
            }
        });

        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent family = new Intent(MainActivity.this, FamilyActivity.class);
                // Start the new activity
                startActivity(family);
            }
        });

        final TextView color = (TextView) findViewById(R.id.colors);
        color.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent color = new Intent(MainActivity.this, ColorsActivity.class);
                // Start the new activity
                startActivity(color);
            }
        });

        TextView phrases = findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent phrases = new Intent(MainActivity.this, PhrasesActivity.class);
                // Start the new activity
                startActivity(phrases);
            }
        });
    }
}
