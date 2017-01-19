package com.example.faeezkmuhammed.sprint3;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    EditText editid;
    EditText editpassword;
    Button btnlogin;
    Button btncreate;
    TextView tvmsg;
    public static String url ="http://services.trainees.baabtra.com/BM-41756faeez/loginsrvc.php";
    public  static String key_id="emailid";
    public static  String key_password="password";
    String id;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.layout);
        View view=getSupportActionBar().getCustomView();
        editid =(EditText)findViewById(R.id.editname);
        editpassword=(EditText)findViewById(R.id.editpassword);
        btnlogin=(Button)findViewById(R.id.button);
        btncreate=(Button)findViewById(R.id.button2);
        tvmsg =(TextView)findViewById(R.id.tverrormsg);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
            }
        });


    }
    private void login (){

        id=editid.getText().toString();
        password=editpassword.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if(response.contains("200")||response.contains("success")){
                           profile();
                        }else{
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                            tvmsg.setVisibility(View.VISIBLE);


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(key_id,id);
                map.put(key_password,password);
                return map;
            }
        };
        RequestQueue reqq = Volley.newRequestQueue(this);
        reqq.add(stringRequest);




    }

    private void profile()
    {
        Intent intend =new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intend);
    }



}
