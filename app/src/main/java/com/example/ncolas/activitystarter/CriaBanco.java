package com.example.ncolas.activitystarter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.nio.channels.Pipe.open;

/**
 * Created by Nícolas on 10/09/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String DB_NAME = "MY_DB_STRATEGIES.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "STRATEGIES";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "STRATEGY_NAME";
    private static final String COL_3 = "CHARACTER";



    public CriaBanco(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //TODO: insert AUTOINCREMENT AND PRIMARY KEY if doesn't work
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                +  COL_1 + " INTEGER PRIMARY KEY,"
                +  COL_2 + " TEXT,"
                +  COL_3 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String strategyName,String character)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strategyName);
        contentValues.put(COL_3,character);
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

        int i = db.delete(TABLE_NAME,"ID=?", new String[] {id});
        return i;
    }

    public boolean updateData(String id, String strategyName, String character)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strategyName);
        contentValues.put(COL_3,character);
        int result = db.update(TABLE_NAME, contentValues, "ID=?", new String[] {id});
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
        //db.execSQL("REINDEX");
        db.execSQL("REINDEX");
        Cursor res = db.rawQuery("Select * from " +TABLE_NAME,null);
        return res;
    }

}









