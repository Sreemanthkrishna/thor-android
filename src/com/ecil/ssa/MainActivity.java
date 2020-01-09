package com.ecil.ssa;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button r,l,dis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		r=(Button)findViewById(R.id.button1);
		l=(Button)findViewById(R.id.button2);
		dis=(Button)findViewById(R.id.button3);
		r.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(MainActivity.this,Userreg.class);
				startActivity(a);
				
			}
		});
		l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(MainActivity.this,Login.class);
				startActivity(a);
			}
		});
		
		
         dis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(MainActivity.this,Display.class);
				startActivity(a);
			}
		});
		
		
	}


}
