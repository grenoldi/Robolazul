package com.example.ncolas.activitystarter;

/**
 * Created by bob on 3/7/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBRobots extends SQLiteOpenHelper {

    private static final String DB_NAME = "MY_DB_STRATEGIES.db";
    private static final int DB_VERSION = 1;
    private final String TABLE_NAME = "ROBOTS";
    private final String COL_1 = "ID";
    private final String COL_2 = "ROBOT_NAME";
    private final String COL_3 = "CATEGORY";



    public DBRobots(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public int getDB_VERSION() {
        return DB_VERSION;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getCOL_1() {
        return COL_1;
    }

    public String getCOL_2() {
        return COL_2;
    }

    public String getCOL_3() {
        return COL_3;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //TODO: insert AUTOINCREMENT AND PRIMARY KEY if doesn't work
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                +  COL_1 + " INTEGER,"
                +  COL_2 + " TEXT PRIMARY KEY,"
                +  COL_3 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addRobot(int id, String robotName,String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2,robotName);
        contentValues.put(COL_3,category);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        //To Check Whether Data is Inserted in DataBase
        if(result==-1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    //TODO: fix this whole function

    /*
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_NAME,"STRATEGY_NAME=?", new String[] {id});
        return i;
    }
    */

    public boolean updateRobot(int id, String robotName, String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, robotName);
        contentValues.put(COL_3, category);
        int result = db.update(TABLE_NAME, contentValues, "STRATEGY_NAME=?", new String[] {robotName});
        if(result > 0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public Cursor getAllRobots(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("REINDEX");
        Cursor res = db.rawQuery( "Select * from " + table, null);
        return res;
    }

    public Cursor getAllCategories(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("REINDEX");
        Cursor res = db.rawQuery("SELECT DISTINCT CATEGORY from " + table, null);
        return res;
    }
}









