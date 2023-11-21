package com.example.LamBaoChauCW1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class BackgroundActivity extends AppCompatActivity {
    View tvwelcome,tvletstarted;
    ImageView imgbackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        tvwelcome = findViewById(R.id.tvWelcome);
        tvwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickHome();
            }
        });
        tvletstarted = findViewById(R.id.tvLetstarted);
        tvletstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickHome();
            }
        });
        imgbackground = findViewById(R.id.imgBackGround);
        imgbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickHome();
            }
        });
    }
    public void ClickHome(){
        Intent notifyIntent = new Intent(this, HomeActivity.class);
        startActivity(notifyIntent);
    }

}