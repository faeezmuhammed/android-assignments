package com.example.faeezkmuhammed.assignment10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView tv=(TextView)findViewById(R.id.txtvu);
        Intent i=getIntent();
        String value=i.getStringExtra("nme").toString();

        tv.setText(value);


    }
}
