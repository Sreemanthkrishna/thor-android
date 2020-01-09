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

public class Userreg extends Activity implements OnClickListener{
	EditText id,ps,em,ph;
	Button su;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userreg);
		id=(EditText)findViewById(R.id.editText1);
		ps=(EditText)findViewById(R.id.editText2);
		em=(EditText)findViewById(R.id.editText3);
		ph=(EditText)findViewById(R.id.editText4);
		su=(Button)findViewById(R.id.button1);
        su.setOnClickListener(this);
        db=openOrCreateDatabase("SSA", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS user(id VARCHAR,pass VARCHAR,email VARCHAR,phone VARCHAR);");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==su)
    	{
    		if(id.getText().toString().trim().length()==0||
    		   ps.getText().toString().trim().length()==0||
    		   em.getText().toString().trim().length()==0||ph.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		else if(ph.getText().toString().trim().length()!=10)
    		{
    			showMessage("Error", "Enter 10 Digit Mobile Number");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM user WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			showMessage("Error", "User Already Exist");
    			return;
    		}
    		else
    		{
    			db.execSQL("INSERT INTO user VALUES('"+id.getText()+"','"+ps.getText()+
     				   "','"+em.getText()+"','"+ph.getText()+"');");
     		showMessage("Success", "Record added");
     		clearText();	
    		}
    		
    		
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
    	ps.setText("");
    	em.setText("");
    	ph.setText("");
    }
	

}
