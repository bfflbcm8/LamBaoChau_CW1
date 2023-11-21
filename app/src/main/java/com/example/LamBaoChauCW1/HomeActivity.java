package com.example.LamBaoChauCW1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText hnameofHike, hlocation, hdateoftheike, hlengththehike, hphone;
    Switch hswitchparking;
    Spinner hspinnerlevel;
    Button hbtsubmit, hbtdetailts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hnameofHike = findViewById(R.id.txtNameofhike);
        hlocation = findViewById(R.id.txtLocation);
        hdateoftheike = findViewById(R.id.dDateofthehike);
        hswitchparking = findViewById(R.id.switchParking);
        hswitchparking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SwitchParking();
            }
        });
        hlengththehike = findViewById(R.id.txtLengththehike);
        hspinnerlevel = findViewById(R.id.spinnerLevelofDifficulty);
        hphone = findViewById(R.id.txtPhone);

        hbtsubmit = findViewById(R.id.btSubmit);
        hbtsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeNotempty();

            }
        });
        hbtdetailts = findViewById(R.id.btDetailts);
        hbtdetailts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeDetailts();
            }
        });
    }

    public void insertHomeMain(){
        try {
            String nameofhike = hnameofHike.getText().toString();
            String location = hlocation.getText().toString();
            String dateofthehike = hdateoftheike.getText().toString();
            String parking = hswitchparking.getText().toString();
            String lengththehike = hlengththehike.getText().toString();
            String spinnerlevel = hspinnerlevel.getSelectedItem().toString();
            String phone = hphone.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("HikeWalkDb", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if Not EXISTS CustomerTB(id INTEGER primary key autoincrement,nameofhike Varchar," +
                    "location Varchar,dateofthehike Varchar," + "parking Varchar," +
                    "lengththehike Varchar,spinnerlevel Varchar, phone Varchar)");
            String sql = "insert into CustomerTB(nameofhike,location,dateofthehike,parking,lengththehike,spinnerlevel,phone )values(?,?,?,?,?,?,?)";
            SQLiteStatement statement= db.compileStatement(sql);
            statement.bindString(1,nameofhike);
            statement.bindString(2,location);
            statement.bindString(3,dateofthehike);
            statement.bindString(4,parking);
            statement.bindString(5,lengththehike);
            statement.bindString(6,spinnerlevel);
            statement.bindString(7,phone);
            statement.execute();
            Toast.makeText(this,"Submit Successfully",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Submit Failed!",Toast.LENGTH_LONG).show();
        }
    }
    public  void HomeDetailts(){
        Intent notifyIntent = new Intent(this, HomeDetailtsActivity.class);
        startActivity(notifyIntent);
    }
    public void SwitchParking() {
        hswitchparking.setText("" +
                (hswitchparking.isChecked() ? "Yes" : "No"));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    public void HomeNotempty(){
      String  emptyNameofhike = hnameofHike.getText().toString();
      String  emptyhlocation = hlocation.getText().toString();
      String  emptydateoftheike = hdateoftheike.getText().toString();
      String  emptylengththehike = hlengththehike.getText().toString();
      String  emptyphone = hphone.getText().toString();
        if (emptyNameofhike.matches("")) {
            Toast.makeText(this, "You not empty Name of Hike!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emptyhlocation.matches("")) {
            Toast.makeText(this, "You not empty Location!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emptydateoftheike.matches("")) {
            Toast.makeText(this, "You not empty Date of The Hike!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emptylengththehike.matches("")) {
            Toast.makeText(this, "You not empty empty Length The Hike!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emptyphone.matches("")) {
            Toast.makeText(this, "You not empty Phone!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            insertHomeMain();
            hbtdetailts.setVisibility(View.VISIBLE);
        }
    }



}