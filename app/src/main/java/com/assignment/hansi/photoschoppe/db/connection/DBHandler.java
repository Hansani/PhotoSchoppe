package com.assignment.hansi.photoschoppe.db.connection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignment.hansi.photoschoppe.model.Photographer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansanibiyanwila on 7/25/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "photoshoppe.db";
    public static final String LOCATION = "/data/data/com.assignment.hansi.photoschoppe/databases/";
    public static final int DB_VERSION = 1;
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

    public static String getDbName() {
        return DB_NAME;
    }

    public static int getDbVersion() {
        return DB_VERSION;
    }

    //open the database
    public void openDataBase(){
        String dbpath = contextdb.getDatabasePath(DB_NAME).getPath();
        if (photoSchoppeDB != null && photoSchoppeDB.isOpen()) {
            return;
        }
        photoSchoppeDB = SQLiteDatabase.openDatabase(dbpath, null, SQLiteDatabase.OPEN_READONLY);
    }

    //close the database
    public void closeDataBase(){
        if (photoSchoppeDB != null){
            photoSchoppeDB.close();
        }
    }

    public List<Photographer> getAllPGs() {
        Photographer photographer = null;
        List<Photographer> photographerList = new ArrayList<>();
        openDataBase();
        Log.d("open_db", String.valueOf(photoSchoppeDB.isOpen()));
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
        closeDataBase();
        return photographerList;
    }
}
