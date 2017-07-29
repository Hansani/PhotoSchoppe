package com.assignment.hansi.photoschoppe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansanibiyanwila on 7/25/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "photoschoppe.db";
    public static final String LOCATION = "data/data/com.assignment.hansi.photoschoppe/databases";
    private static final Integer DB_VERSION = 1;
    public Context contextdb;
    private SQLiteDatabase photoSchoppeDB;


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.contextdb = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void openDatabase(){
        String dbpath = contextdb.getDatabasePath(DB_NAME).getPath();
        if (photoSchoppeDB != null && photoSchoppeDB.isOpen()) {
            return;
        }
        photoSchoppeDB = SQLiteDatabase.openDatabase(dbpath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void closeDatabase(){
        if (photoSchoppeDB!= null){
            photoSchoppeDB.close();
        }
    }

    public List<Photographer> getAllPGs() {
        Photographer photographer = null;
        List<Photographer> photographerList = new ArrayList<>();
        openDatabase();
        if (photoSchoppeDB.isOpen()) {
            Cursor cursor = photoSchoppeDB.rawQuery("SELECT * FROM photographer", null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()) {
                photographer = new Photographer(cursor.getString(0).toString(),
                        cursor.getString(1).toString(), cursor.getString(2).toString(), cursor.getString(3).toString());
                photographerList.add(photographer);
                cursor.moveToNext();
            }
            cursor.close();
        }
        closeDatabase();
        return photographerList;
    }
}
