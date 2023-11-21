package com.example.LamBaoChauCW1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeDetailtsActivity extends AppCompatActivity {
    ListView hdlistview;
    ArrayList<String> hdTitle= new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detailts);

        SQLiteDatabase db = openOrCreateDatabase("HikeWalkDb", Context.MODE_PRIVATE,null);

        hdlistview = findViewById(R.id.hdListView);
        final Cursor cs = db.rawQuery("Select * From CustomerTB",null);
        int id = cs.getColumnIndex("id");
        int nameofhike = cs.getColumnIndex("nameofhike");
        int location = cs.getColumnIndex("location");
        int dateofthehike = cs.getColumnIndex("dateofthehike");
        int parking = cs.getColumnIndex("parking");
        int lengththehike = cs.getColumnIndex("lengththehike");
        int spinnerlevel = cs.getColumnIndex("spinnerlevel");
        int phone = cs.getColumnIndex("phone");
        hdTitle.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,hdTitle);
        hdlistview.setAdapter(arrayAdapter);

        final ArrayList<HomeCustomer> hCustomer = new ArrayList<HomeCustomer>();

        if(cs.moveToFirst()){
            hdTitle.add( "Name " +"   Location  " + "           Date "
                    + "             length" + "        Level"  );
            do{
                HomeCustomer hcustomer = new HomeCustomer();
                hcustomer.id = cs.getString(id);
                hcustomer.nameofhike = cs.getString(nameofhike);
                hcustomer.location = cs.getString(location);
                hcustomer.dateofthehike = cs.getString(dateofthehike);
                hcustomer.parking = cs.getString(parking);
                hcustomer.lengththehike = cs.getString(lengththehike);
                hcustomer.spinnerlevel = cs.getString(spinnerlevel);
                hcustomer.phone = cs.getString(phone);

                hCustomer.add(hcustomer);
                
                hdTitle.add(cs.getString(nameofhike) + " \t "  + cs.getString(location)
                        + " \t" + cs.getString(dateofthehike) + " \t "
                        + "\t "  + cs.getString(lengththehike) + " \t "  + cs.getString(spinnerlevel) );
            }while (cs.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            hdlistview.invalidateViews();
        }

    }
}