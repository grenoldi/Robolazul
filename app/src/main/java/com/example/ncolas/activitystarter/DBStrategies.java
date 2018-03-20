package com.example.ncolas.activitystarter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nícolas on 10/09/2017.
 */

public class DBStrategies extends SQLiteOpenHelper {

    private static final String DB_NAME = "FEG_ROBOTICA.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "STRATEGIES";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "STRATEGY_NAME";
    private static final String COL_3 = "CHARACTER";
    private static final String COL_4 = "ROBOT_NAME";



    public DBStrategies(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //TODO: insert AUTOINCREMENT AND PRIMARY KEY if doesn't work
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                +  COL_1 + " INTEGER UNIQUE,"
                +  COL_2 + " TEXT PRIMARY KEY,"
                +  COL_3 + " TEXT,"
                +  COL_4 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String strategyName,String character, String robotName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strategyName);
        contentValues.put(COL_3,character);
        contentValues.put(COL_4,robotName);
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

    //TODO: fix ID indexes sequences and use class Estratégia
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_NAME,"STRATEGY_NAME=?", new String[] {id});
        return i;
    }

    public boolean updateData(String strategyName, String character)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strategyName);
        contentValues.put(COL_3,character);
        int result = db.update(TABLE_NAME, contentValues, "STRATEGY_NAME=?", new String[] {strategyName});
        if(result >0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }



    public Cursor getAllStrategies()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='"+TABLE_NAME+"'");
        db.execSQL("REINDEX");
        Cursor res = db.rawQuery("Select * from " +TABLE_NAME,null);
        return res;
    }

}









