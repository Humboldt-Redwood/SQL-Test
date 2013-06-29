/********************************************************************
* Main Activity                                                     *
*                                                                   *
* Author: Radiac                                                    *
*                                                                   *
* Last Modified: July 1, 2013                                       *
*                                                                   *
* Version: 0.1                                                      *
*                                                                   *
*********************************************************************/

package com.example.sqltest;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	         
	        DatabaseHandler db = new DatabaseHandler(this);
	         
	        
	        Calendar cal = Calendar.getInstance(); 

	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH)+1; // January = 0 !
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        
	        String date = day + "." + month + "." + year; 
  
	        /**
	         * CRUD Operations
	         * */
	        // Inserting Contacts
	        Log.i("Insert: ", "Inserting ..");
	        db.addResult(new GameResult(1, "Radiac", "21" , date));       
	        db.addResult(new GameResult(1, "Zodiac", "22" , date));
	        db.addResult(new GameResult(1, "Bart", "15" , date));
	        db.addResult(new GameResult(1, "McClane", "17" , date));
	         
	        // Reading all contacts
	        Log.d("Reading: ", "Reading all contacts..");
	        List<GameResult> contacts = db.getAllResults();      
	         
	        for (GameResult cn : contacts) {
	            String log = "Id: "+cn.getID()+"  Level:" +cn.getLevel() + "  Name: " + cn.getName() + "  Phone: " + cn.getMoves() + "  Date: " + cn.getDate();
	            // Writing results to log
	            Log.d("Name: ", log);
	        }
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
