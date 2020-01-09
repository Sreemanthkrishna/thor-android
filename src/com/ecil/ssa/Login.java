package com.ecil.ssa;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText user, pass;
	Button a;
	 String u;
	 String p;
	 SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_login);
		user = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		a=(Button) findViewById(R.id.button1);
		
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(user.getText().toString().equals("")||pass.getText().toString().equals("")){
					
					Toast.makeText(Login.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					 u = user.getText().toString();
					 p = pass.getText().toString();
					 try{
					          db=openOrCreateDatabase("SSA",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }try{
					        	 Cursor cc = db.rawQuery("select * from user where id = '"+u+"' and pass = '"+p+"' ", null);
					        	 if(user.getText().toString().equals("ecil")&& pass.getText().toString().equals("ecil")){
					        		 Toast.makeText(Login.this, "Welcome To Admin Home Page "  + u , Toast.LENGTH_LONG).show();
					        		 globalvariabel.Setusername(user.getText().toString().trim());
					    				globalvariabel.Setpassword(pass.getText().toString().trim());	
					        		 Intent i = new Intent(Login.this,Adminhome.class);
					            		startActivity(i);
					            		clearText();
									}
					        	 // User Login
					        	 else if(cc.moveToFirst())
					        		 {String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            	//return true;
						            scan g=new scan();
						            g.execute();
						            
						            		Toast.makeText(Login.this, "Welcome To User Home Page "  + u , Toast.LENGTH_LONG).show();
						            		globalvariabel.Setusername(user.getText().toString().trim());
						    				globalvariabel.Setpassword(pass.getText().toString().trim());
						            		Intent i = new Intent(Login.this,Userhome.class);
						            		startActivity(i);
						            		clearText();
						            	}else{
						            		 Toast.makeText(Login.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
					        		 }
					        	 else{
				            		 Toast.makeText(Login.this, "Enter Proper User Id/Password..!", Toast.LENGTH_LONG).show();
				            	}
						            //	return false;
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }
						}	 	
				}
		});
		
	
		
		
	}
	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(Login.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	

	public void clearText()
    {
    	user.setText("");
    	pass.setText("");
    }
}

