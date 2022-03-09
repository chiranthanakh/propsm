package com.proteam.projectstoremanagement.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDb extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "PSM.db";
    public static final String TABLE_NAME = "notification";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "msg";
    public static final String COL_3 = "date";
    public static final String COL_4 = "title";


    public SqlDb(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,msg TEXT ,date TEXT,title TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String msg, String title, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,msg);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,title);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    /*public int getUpdate2(int snNO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL4,"false");
        int res=db.update(TABLE_SECKOND, cv, "SN="+snNO, null);
        return res;

    }*/

    /*public Cursor getsn() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_SECKOND, null);
        return res;
    }*/

    public void deleteItem() {
        SQLiteDatabase db = getWritableDatabase();
        //String whereArgs[] = {item.id.toString()};
        //db.delete( TABLE_NAME,COL_1 + "=" + item,null);
        db.execSQL("delete from "+TABLE_NAME );

    }
}
