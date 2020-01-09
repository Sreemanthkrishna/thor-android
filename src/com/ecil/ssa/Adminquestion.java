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
import android.widget.Toast;

public class Adminquestion extends Activity implements OnClickListener{
	EditText id;
	Button view,del;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminquestion);
		id=(EditText)findViewById(R.id.editText1);
		view=(Button)findViewById(R.id.button1);
		view.setOnClickListener(this);
		del=(Button)findViewById(R.id.button2);
		del.setOnClickListener(this);
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
		
		//del
		if(v==del)
    	{
    		if(id.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please User Id");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM ecil WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("DELETE FROM ecil WHERE id='"+id.getText()+"'");
    			Toast.makeText(Adminquestion.this, "Question Deleted", Toast.LENGTH_SHORT).show();
    		}
    		else
    		{
    			Toast.makeText(Adminquestion.this, "Invalid Id", Toast.LENGTH_SHORT).show();
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
    }

}
