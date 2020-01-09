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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddQuestion extends Activity implements OnClickListener{
	TextView id;
	EditText gn,qs;
	Button sub,status;
	RadioButton ac ;
	RadioGroup acc;
	SQLiteDatabase db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_question);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		id=(TextView)findViewById(R.id.textView2);
		id.setText(globalvariabel.GetUsername().toString());
		gn=(EditText)findViewById(R.id.editText2);
		acc=(RadioGroup)findViewById(R.id.radioGroup1);
		sub=(Button)findViewById(R.id.button1);
		sub.setOnClickListener(this);
		status=(Button)findViewById(R.id.button2);
		status.setOnClickListener(this);
		db=openOrCreateDatabase("SSA", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS ecil(id VARCHAR,grou VARCHAR,qution VARCHAR,status VARCHAR,rating VARCHAR);");
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==sub)
    	{
			int ssid=acc.getCheckedRadioButtonId();
			ac=(RadioButton)findViewById(ssid);
    		if(id.getText().toString().trim().length()==0||
    		   gn.getText().toString().trim().length()==0||ac.getText().toString().trim().length()==0)
    		{
    			Toast.makeText(AddQuestion.this, "Enter Proper Values.....?", Toast.LENGTH_SHORT).show();
    			return;
    		}
    		db.execSQL("INSERT INTO ecil VALUES('"+id.getText()+"','"+ac.getText()+
    				   "','"+gn.getText()+"','"+null+"','"+null+"');");
    		Toast.makeText(AddQuestion.this, "Question Sent Successfully", Toast.LENGTH_SHORT).show();
    		clearText();
    	}
		
		if(v==status)
    	{
    		if(id.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM ecil WHERE id='"+id.getText()+"'", null);
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
    			buffer.append("Status: "+c.getString(3)+"\n");
    			buffer.append("Rating: "+c.getString(4)+"\n");
    		}
    		showMessage("User Question Details", buffer.toString());
    		
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
    	gn.setText("");
    }

}
