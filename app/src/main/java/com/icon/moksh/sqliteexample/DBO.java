package com.icon.moksh.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBO extends SQLiteOpenHelper {

SQLiteDatabase db;
ContentValues cv;
    public  DBO(Context context)
    {
        super(context,"School",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table tblstudent (sid integer primary key autoincrement , sname text, sarea text, sphone text)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tblstudent");
        onCreate(db);
    }


    void add(String name, String area, String phone) {
    db = this.getWritableDatabase();
    cv = new ContentValues();
    cv.put("sname",name);
    cv.put("sarea",area);
    cv.put("sphone",phone);
    db.insert("tblstudent",null,cv);
    }


    void edit(int id,String name, String area, String phone)
    {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("sname",name);
        cv.put("sarea",area);
        cv.put("sphone",phone);
        db.update("tblstudent",cv,"sid=?",new String[]{String.valueOf(id)});
    }

    void delete(int id)
    {
        db= this.getWritableDatabase();
        db.delete("tblstudent","sid=?",new String[]{String.valueOf(id)});
    }

    public Cursor getAllStudent()
    {
        db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from tblstudent",null);
        return result;
    }

    public void Delete_Database(){
        onUpgrade(db,1,1);
    }
}
