package com.example.sqltest;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database version
    private static final int DATABASE_VERSION = 1; 
    
    // Database name
    private static final String DATABASE_NAME = "BoulderDashHighscores";
    
    // Table name
    private static final String HIGHSCORES = "highscore"; 
 
    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LEVEL_ID = "level_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOVES = "moves";
    private static final String KEY_DATE = "date";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    
    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + HIGHSCORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LEVEL_ID + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_MOVES + " TEXT,"
                + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    
    // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + HIGHSCORES);
        onCreate(db);
    }
    
    
    // Adding new result
    public void addResult(GameResult result) { 
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_LEVEL_ID, result.getLevel());
        values.put(KEY_NAME, result.getName()); 
        values.put(KEY_MOVES, result.getMoves()); 
        values.put(KEY_DATE, result.getDate());
     
        db.insert(HIGHSCORES, null, values); // Inserting Row
        db.close(); // Closing database connection
    }
    
    
    // Getting single result
    public GameResult getResult(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(HIGHSCORES, new String[] { KEY_ID, KEY_LEVEL_ID, 
                KEY_NAME, KEY_MOVES, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        GameResult gameResult = new GameResult(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)) ,
                cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return gameResult;
    }
    
    
    // Getting all results
    public List<GameResult> getAllResults() {
       List<GameResult> resultList = new ArrayList<GameResult>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + HIGHSCORES;
    
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
    
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               GameResult result = new GameResult();
               result.setID(Integer.parseInt(cursor.getString(0)));
               result.setLevel(Integer.parseInt(cursor.getString(1)));
               result.setName(cursor.getString(2));
               result.setMoves(cursor.getString(3));
               result.setDate(cursor.getString(4));             
               resultList.add(result); // Adding contact to list
           } while (cursor.moveToNext());
       }
       return resultList;
   }
    
    
 // Getting results count
    public int getGameResultsCount() {
        String countQuery = "SELECT  * FROM " + HIGHSCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    
    // Updating single game result
    public int updateGameResult(GameResult result) {
    	
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_LEVEL_ID, result.getLevel());
	    values.put(KEY_NAME, result.getName());
	    values.put(KEY_MOVES, result.getMoves());
	    values.put(KEY_DATE, result.getDate());
	 
	    // updating row
	    return db.update(HIGHSCORES, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(result.getID()) });
    }
    
    
    // Deleting single result
    public void deleteGameResult(GameResult result) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HIGHSCORES, KEY_ID + " = ?",
                new String[] { String.valueOf(result.getID()) });
        db.close();
    }
      
}