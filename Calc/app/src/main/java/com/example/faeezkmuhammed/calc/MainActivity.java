package com.example.faeezkmuhammed.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double value1;
    double value2;
    double result;
    EditText val1;
    EditText val2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText val1=(EditText)findViewById(R.id.editText);
        final EditText val2=(EditText)findViewById(R.id.editText2);
        Button btnadd=(Button)findViewById(R.id.add);
        Button btnsub=(Button)findViewById(R.id.sub);
        Button btnmul=(Button)findViewById(R.id.mult);
        Button btndiv=(Button)findViewById(R.id.div);
       final TextView tv=(TextView)findViewById(R.id.textView);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value1=Double.parseDouble(val1.getText().toString());
                value2=Double.parseDouble(val2.getText().toString());
                if (val1.getText().toString().equals("")||val2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "value cannot be empty", Toast.LENGTH_SHORT).show();
                }
                result =value1+value2;
                tv.setText(Double.toString(result));
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value1=Double.parseDouble(val1.getText().toString());
                value2=Double.parseDouble(val2.getText().toString());
                if (val1.getText().toString().equals("")||val2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "value cannot be empty", Toast.LENGTH_SHORT).show();
                }
                result =value1-value2;
                tv.setText(Double.toString(result));
            }
        });

        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value1=Double.parseDouble(val1.getText().toString());
                value2=Double.parseDouble(val2.getText().toString());
                if (val1.getText().toString().equals("")||val2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "value cannot be empty", Toast.LENGTH_SHORT).show();
                }
                result =value1*value2;
                tv.setText(Double.toString(result));
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value1=Double.parseDouble(val1.getText().toString());
                value2=Double.parseDouble(val2.getText().toString());
                if (val1.getText().toString().equals("")||val2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "value cannot be empty", Toast.LENGTH_SHORT).show();
                }
                if (value2==0||(value1==0&&value2==0) ){
                    Toast.makeText(MainActivity.this, "Division by zero", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = value1 / value2;
                    tv.setText(Double.toString(result));
                }

            }
        });



    }
}
