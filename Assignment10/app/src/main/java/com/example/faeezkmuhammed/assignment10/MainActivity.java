package com.example.faeezkmuhammed.assignment10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent in =new Intent(this,Activity2.class);
        Button btn=(Button) findViewById(R.id.button);
        final EditText tv=(EditText) findViewById(R.id.editText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tv.getText().toString();
                in.putExtra("nme",name);
                startActivity(in);
            }
        });


    }
}
