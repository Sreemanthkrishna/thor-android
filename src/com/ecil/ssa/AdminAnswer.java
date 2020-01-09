package com.ecil.ssa;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AdminAnswer extends Activity implements OnClickListener{
	EditText id,ans;
	Button view,up;
	SQLiteDatabase db;
	RatingBar acc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_answer);
		id=(EditText)findViewById(R.id.editText1);
		ans=(EditText)findViewById(R.id.editText2);
		acc=(RatingBar)findViewById(R.id.ratingBar1);
		view=(Button)findViewById(R.id.button1);
		view.setOnClickListener(this);
		up=(Button)findViewById(R.id.button2);
		up.setOnClickListener(this);
		db=openOrCreateDatabase("SSA", Context.MODE_PRIVATE, null);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==view)
    	{
    	
    		Cursor c=db.rawQuery("SELECT * FROM ecil", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("User Id: "+c.getString(0)+"\n");
    			buffer.append("Group: "+c.getString(1)+"\n");
    			buffer.append("Question: "+c.getString(2)+"\n");
    			buffer.append("Reply: "+c.getString(3)+"\n");
    			buffer.append("Rating: "+c.getString(4)+"\n\n");
    		}
    		showMessage("User Question Details", buffer.toString());
    	}
		if(v==up)
    	{
			
    		if(id.getText().toString().trim().length()==0||ans.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Valid Details");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM ecil WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("UPDATE ecil SET status='"+ans.getText()+"',rating='"+acc.getRating()+"' WHERE id='"+id.getText()+"'");
    			showMessage("Success", "Answer Sent Successfully");
    		}
    		else
    		{
    			showMessage("Error", "Invalid Rollno");
    		}
    		clearText();
    	}
		
	}
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
	public void clearText()
    {
    	id.setText("");
    	ans.setText("");
    }

}
