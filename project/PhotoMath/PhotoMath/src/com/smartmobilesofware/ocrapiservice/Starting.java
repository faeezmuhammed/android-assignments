package com.smartmobilesofware.ocrapiservice;




import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Starting extends Activity implements OnClickListener {

	Button bt1;
	EditText ed1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);
		
		bt1=(Button) findViewById(R.id.button1);
		ed1=(EditText) findViewById(R.id.editText1);
		
		bt1.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if(arg0==bt1)
		{
			if(ed1.getText().toString().trim().length()==0)
			{
				ed1.setError("Missing");
			}
			else
			{
		   SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		   Editor ed= sh.edit();
		   ed.putString("ip", ed1.getText().toString());
		   ed.putString("url", "http://"+ed1.getText().toString()+"/WebSite2/WebService.asmx");
		   ed.commit();
			
		   Intent int_main=new Intent(getApplicationContext(), MainActivity.class);
		   startActivity(int_main);
			}
				   
		}
		// TODO Auto-generated method stub
		
	}

}
