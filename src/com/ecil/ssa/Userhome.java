package com.ecil.ssa;


	import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.database.Cursor;
import android.view.View.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

	public class Userhome extends Activity implements OnClickListener{
	     Button aq,vq,vp,logout;
	     TextView ui;
	     SQLiteDatabase db;

	     	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_userhome);
			final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
			ui = (TextView)findViewById(R.id.textView2);
			ui.setText(globalvariabel.GetUsername().toString());
			vq=(Button)findViewById(R.id.button3);
			aq=(Button)findViewById(R.id.button1);
			vq.setOnClickListener(this);
			logout=(Button)findViewById(R.id.button4);
			db=openOrCreateDatabase("SSA", Context.MODE_PRIVATE, null);
			aq.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(Userhome.this,AddQuestion.class);
			startActivity(i);
		}
	});
			
			logout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Userhome.this,MainActivity.class);
					startActivity(i);
				}
			});
	
	}
	public void showMessage(String title,String message)
	{
	Builder builder=new Builder(this);
	builder.setCancelable(true);
	builder.setTitle(title);
	builder.setMessage(message);
	builder.show();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==vq)
    	{
    		if(ui.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM user WHERE id='"+ui.getText()+"'", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Id: "+c.getString(0)+"\n");
    			buffer.append("Password: "+c.getString(1)+"\n");
    			buffer.append("Email: "+c.getString(2)+"\n");
    			buffer.append("Mobile No: "+c.getString(3)+"\n");
    		}
    		showMessage("User Details", buffer.toString());
    		
    	}
		
	}
}

		

