package com.ecil.ssa;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Adminhome extends Activity {
	TextView id;
	Button b1,b2,b3,b4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminhome);

		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		id=(TextView)findViewById(R.id.textView2);
		id.setText(globalvariabel.GetUsername().toString());
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(Adminhome.this,Adminuser.class);
				startActivity(a);
			}
		});
		
		b2=(Button)findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(Adminhome.this,Adminquestion.class);
				startActivity(a);
			}
		});
		
		b3=(Button)findViewById(R.id.button3);
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(Adminhome.this,AdminAnswer.class);
				startActivity(a);
			}
		});
		b4=(Button)findViewById(R.id.button4);
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(Adminhome.this,MainActivity.class);
				startActivity(a);
			}
		});
	}



}
