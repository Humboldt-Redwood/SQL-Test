package com.example.sqltest;

public class GameResult {
    
    //private variables
    private int id;
    private int level;
    private String name;
    private String moves;
    private String date;
     
    
    // Empty constructor
    public GameResult(){
         
    }
    
    // constructor
    public GameResult(int id,int level, String name, String _phone_number, String date){
        this.id = id;
        this.level = level;
        this.name = name;
        this.moves = _phone_number;
        this.date = date;
    }
     
    
    // constructor
    public GameResult(int level, String name, String _phone_number, String date){
    	this.level = level;
        this.name = name;
        this.moves = _phone_number;
        this.date = date;
    }
    
    
    // getting ID
    public int getID(){
        return this.id;
    }
     
    
    // setting id
    public void setID(int id){
        this.id = id;
    }
    
    
    // getting level
    public int getLevel(){
        return this.level;
    }
     
    
    // setting level
    public void setLevel(int level){
        this.level = level;
    }
     
    
    // getting name
    public String getName(){
        return this.name;
    }
     
    // setting name
    public void setName(String name){
        this.name = name;
    }
     
    
    // getting move
    public String getMoves(){
        return this.moves;
    }
     
    
    // setting move
    public void setMoves(String moves){
        this.moves = moves;
    }
    
    
    // getting date
    public String getDate(){
        return this.date;
    }
     
    
    // setting date
    public void setDate(String date){
        this.date = date;
    }
    
   
}