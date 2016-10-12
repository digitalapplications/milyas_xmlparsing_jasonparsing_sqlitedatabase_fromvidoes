package com.example.l400.xmlparsing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by l400 on 10/4/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "employe.db";
    public static final int VERSION = 6;
    public static final String TABLE_NAME = "tours";
    public static final  String  COL_ID = "id";
    public static final  String  COL_NAME = "name";
    public static final  String  COL_DEPARTMENT = "department";
    public static final  String  COL_TYPE = "type";
    public static final  String  COL_EMAIL = "email";
    private static final String TABLE_CREATE = "CREATE TABLE tours (id int primary key, name  text,department text, type text, email text)";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL(TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
        Log.d("data" , "database has been upgraded from " );
    }
    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String name = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(name,null);
        return cursor;
    }







}
