package com.example.faeezkmuhammed.assignment6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn;
        final EditText edt1,edt2;
        btn = (Button)findViewById(R.id.btnid);
        edt1=(EditText)findViewById(R.id.edt1id);
        edt2=(EditText)findViewById(R.id.edt2id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                str=edt1.getText().toString();
                edt2.setText(str);
                Toast.makeText(MainActivity.this,str, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
