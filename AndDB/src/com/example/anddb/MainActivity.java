package com.example.anddb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText et1;
	Button b1,b2;
	SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        DB=openOrCreateDatabase("Hello",Context.MODE_PRIVATE,null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR);");
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }


    @Override
    public void onClick(View arg0) {
    	if(arg0==b1){
    		Toast.makeText(getApplicationContext(), "BUTTON ADD",
    				Toast.LENGTH_LONG).show();
    				if(et1.getText().toString().trim().length()==0){
    				showmessage("error","Enter valid Input");
    				}
    				else{
    				DB.execSQL("INSERT INTO Student VALUES('"+et1.getText()+"');");
    				showmessage("Sucess","Name Added");
    				}
    				clear();
    				}
    				if(arg0==b2){
    				Cursor c=DB.rawQuery("SELECT * FROM student; ",null );
    				if(c.getCount()==0){
    				showmessage("...","No record Found");
    				return;
    				}
    				StringBuffer buffer=new StringBuffer();
    				 while(c.moveToNext())
    				 {
    				 buffer.append("Name: "+c.getString(0)+"\n");

    				 }
    				 showmessage("Student Details", buffer.toString());
    				}
    				}

    	private void clear() {
		// TODO Auto-generated method stub
    		et1.clearFocus();
		
	}


		private void showmessage(String string, String string2) {
		// TODO Auto-generated method stub
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setCancelable(true);
			builder.setTitle(string);
			builder.setMessage(string2);
			builder.show();
		
	}

		}
   
