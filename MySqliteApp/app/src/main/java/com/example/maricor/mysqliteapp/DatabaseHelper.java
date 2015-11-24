package com.example.maricor.mysqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maricor on 11/17/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {



    //MyDiary
    public static final String DB_NAME = "MyDiary.db";
    public static final String TBL_NAME = "mydiary_table";
    public static final String col1 = "id";
    public static final String col2 = "title";
    public static final String col3 = "description";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
          SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + " " + TBL_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TBL_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, title);
        contentValues.put(col3, desc);
        long result = db.insert(TBL_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TBL_NAME,null);
        return res;

    }

    public Cursor fetchData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String q = "SELECT * FROM" + " " + TBL_NAME +  " " + "WHERE id = " + id  ;
        Cursor res = db.rawQuery(q, null);

        return res;

    }


    public boolean updateData( String id, String title, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, id);
        contentValues.put(col2, title);
        contentValues.put(col3, desc);
        db.update(TBL_NAME, contentValues, "ID = ?", new String[] {id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TBL_NAME, "ID = ?", new String[] {id} );
    }
}
