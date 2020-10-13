package com.example.day11.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "dts.db";
    public static final String TABLE_SQLITE = "biodata";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_SQLITE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTO INCREMENT, " + COLUMN_NAME + " TEXT NOT NULL, " +COLUMN_ADDRESS + " TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SQLITE );
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordlist;
        wordlist = new ArrayList<HashMap<String, String>>();
        String SQL_QUERY = "SELECT * FROM "+ TABLE_SQLITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL_QUERY, null);
        if(cursor.moveToNext()){
            do{
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_ADDRESS, cursor.getString(2));
                wordlist.add(map);
            }while (cursor.moveToNext());
        }

        Log.e("select sql " , ""+ wordlist);
        db.close();
        return wordlist;
    }

    public void insert(String nama, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryinsert = "INSERT INTO "+ TABLE_SQLITE + "(" + nama +"," + address  +") " +
                "VALUES ('" + nama +"','"+address+"')";
        Log.e("insert sqlite",""+queryinsert);
        db.execSQL(queryinsert);
        db.close();
    }

    public void update(int id, String nama, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        String updatequery = "UPDATE "+ TABLE_SQLITE + " SET "
                + COLUMN_NAME + "='" + nama + "',"
                + COLUMN_ADDRESS + "='" + address + "',"
                + "WHERE " + COLUMN_ID + "='" + "'" + id + "'";
        Log.e("update querty",""+updatequery);
        db.execSQL(updatequery);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String deletequery = "DELETE FORM "+ TABLE_SQLITE + " WHERE "
                + COLUMN_ID + "='" + id + "'";
        Log.e("delete query",""+deletequery);
        db.execSQL(deletequery);
        db.close();
    }
}
