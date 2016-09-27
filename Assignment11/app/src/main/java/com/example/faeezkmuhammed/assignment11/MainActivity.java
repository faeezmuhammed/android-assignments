package com.example.faeezkmuhammed.assignment11;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner spnrobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnrobj = (Spinner)findViewById(R.id.spinner);


        spnrobj.setOnItemSelectedListener(MainActivity.this);
        ArrayList<String> dy = new ArrayList<String>();
        dy.add("monday");
        dy.add("tuesday");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrobj.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    String item=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),item, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

