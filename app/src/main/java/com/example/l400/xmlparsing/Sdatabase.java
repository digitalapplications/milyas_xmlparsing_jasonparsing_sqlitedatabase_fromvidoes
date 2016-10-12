package com.example.l400.xmlparsing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l400 on 10/6/2016.
 */
public class Sdatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME1 = "employe1.db";
    public static final int VERSION1 = 6;
    public static final String TABLE_NAME1 = "tours1";
    public static final  String  COL_ID1 = "id1";
    public static final  String  COL_NAME1 = "name1";
    public static final  String  COL_DEPARTMENT1 = "department1";
    public static final  String  COL_TYPE1 = "type1";
    public static final  String  COL_EMAIL1 = "email1";
    SQLiteDatabase db;

   // private static final String TABLE_CREATE1 = " CREATE TABLE " + TABLE_NAME1 + " (id1 int primary key, name1  text,department1 text, type1 text, email1 text)";
    public Sdatabase(Context context) {
        super(context, DATABASE_NAME1, null, VERSION1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " +TABLE_NAME1+ "(name1 TEXT,department1 TEXT,type1 TEXT , email1 TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);

    }
    public boolean inserData(String name,String department ,String type ,String email)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name1",name);
        contentValues.put("department1",department);
        contentValues.put("type1",type);
        contentValues.put("email1",email);
        long result=  db.insert( TABLE_NAME1 ,null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String name = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = db.rawQuery(name,null);
        return cursor;
    }


    public List<Flower> findAll2(){
        Cursor cursor = db.rawQuery("select * from tours1" ,null);
        List<Flower> flowerLists = cursartoList(cursor);
        return flowerLists;
    }
    public List<Flower> cursartoList(Cursor cursor) {
        List<Flower> flowerList = new ArrayList<Flower>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Flower flower = new Flower();
                flower.setName(cursor.getString(cursor.getColumnIndex(COL_NAME1)));
                flower.setDepartment(cursor.getString(cursor.getColumnIndex(COL_DEPARTMENT1)));
                flower.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE1)));
                flower.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL1)));
                flowerList.add(flower);
            }
        }
        return flowerList;
    }
    public void open(){
        Log.d("open" , "database open");
        db = getWritableDatabase();
    }
    public void close(){
        Log.d("close" ,"database close");
        close();
    }



}
