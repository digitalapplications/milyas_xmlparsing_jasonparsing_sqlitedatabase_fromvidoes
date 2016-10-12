package com.example.l400.xmlparsing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l400 on 10/4/2016.
 */
public class DataSource {
    DatabaseHelper databaseHelper;
    Sdatabase sdatabase;
    SQLiteDatabase db;


    private static final String[] colname = {
            DatabaseHelper.COL_ID,
            DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DEPARTMENT,
            DatabaseHelper.COL_TYPE,
            DatabaseHelper.COL_EMAIL
    };

    public DataSource(Context context){
        databaseHelper = new DatabaseHelper(context);
        sdatabase = new Sdatabase(context);
    }
    public void open(){
        Log.d("open" , "database open");
        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        Log.d("close" ,"database close");
        databaseHelper.close();
    }

    public Flower create(Flower flower){
        ContentValues values = new ContentValues();
        values.put(databaseHelper.COL_ID, flower.getId());
        values.put(databaseHelper.COL_NAME,flower.getName());
        values.put(databaseHelper.COL_DEPARTMENT,flower.getDepartment());
        values.put(databaseHelper.COL_TYPE,flower.getType());
        values.put(databaseHelper.COL_EMAIL,flower.getEmail());
         long inserted = db.insert(databaseHelper.TABLE_NAME , null , values);
         flower.setId(inserted);
        return flower;
    }
    public List<Flower> findAll(){
        Cursor cursor = db.rawQuery("select * from " +databaseHelper.TABLE_NAME,null);
        List<Flower> flowerLists = cursartoList(cursor);
        return flowerLists;
    }

    public List<Flower> findAllfiltered(String selection,String orderby){
        Cursor cursor = db.query(databaseHelper.TABLE_NAME,colname,selection,null,null,null,orderby);


        List<Flower> flow = cursartoList(cursor);
        return flow;
    }
    public List<String> getname() {
        //SQLiteDatabase db = this.getWritableDatabase();
        String name = "SELECT * FROM tours";
        List<String> nam = new ArrayList<>();
        Cursor cursor = db.rawQuery(name, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()){
                String n = cursor.getString(cursor.getColumnIndex("name"));

                Log.d("Name",n);
                nam.add(n);
            }
        }
        return nam;
    }

    public List<String> getemail() {
        //SQLiteDatabase db = this.getWritableDatabase();
        String name = "SELECT * FROM tours";
        List<String> nam = new ArrayList<>();
        Cursor cursor = db.rawQuery(name, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()){
                String n = cursor.getString(cursor.getColumnIndex("email"));

                Log.d("Name",n);
                nam.add(n);
            }
        }
        return nam;
    }


    public List<Flower> cursartoList(Cursor cursor) {
        List<Flower> flowerList = new ArrayList<Flower>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Flower flower = new Flower();
                flower.setId(cursor.getLong(cursor.getColumnIndex(databaseHelper.COL_ID)));
                flower.setName(cursor.getString(cursor.getColumnIndex(databaseHelper.COL_NAME)));
                flower.setDepartment(cursor.getString(cursor.getColumnIndex(databaseHelper.COL_DEPARTMENT)));
                flower.setType(cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TYPE)));
                flower.setEmail(cursor.getString(cursor.getColumnIndex(databaseHelper.COL_EMAIL)));
                flowerList.add(flower);

            }

        }
        return flowerList;

    }



}
