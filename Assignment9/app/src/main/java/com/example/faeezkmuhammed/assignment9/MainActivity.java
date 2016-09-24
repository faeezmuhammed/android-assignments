package com.example.faeezkmuhammed.assignment9;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnalrt1 =(Button) findViewById(R.id.btnalert1);
        Button btnalrt2 =(Button) findViewById(R.id.btnalert2);
        Button btnalrt3 =(Button) findViewById(R.id.btnalert3);

        btnalrt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AlertDialog Alert1= new AlertDialog.Builder(MainActivity.this).create();
                Alert1.setTitle("one button alert");
                Alert1.setMessage("This is an example for one button alert");
                Alert1.setIcon(R.drawable.magclip);
                Alert1.setButton(DialogInterface.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "ONE BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });

                Alert1.show();


            }

        });
        btnalrt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog Alert2= new AlertDialog.Builder(MainActivity.this).create();
                Alert2.setTitle("two button alert");
                Alert2.setMessage("This is an example for two button alert");
                Alert2.setIcon(R.drawable.magclip);
                Alert2.setButton(DialogInterface.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "OK pressed for TWO BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert2.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Cancel pressed for TWO BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert2.show();


            }

        });
        btnalrt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog Alert3= new AlertDialog.Builder(MainActivity.this).create();
                Alert3.setTitle("three button alert");
                Alert3.setMessage("This is an example for three button alert");
                Alert3.setIcon(R.drawable.magclip);
                Alert3.setButton(DialogInterface.BUTTON_POSITIVE,"Yes",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Yes pressed for THREE BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert3.setButton(DialogInterface.BUTTON_NEGATIVE,"No",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "No pressed for THREE BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert3.setButton(DialogInterface.BUTTON_NEUTRAL,"Exit",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Exit pressed for THREE BUTTON ALERT", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert3.show();
            }

        });
    }
}
