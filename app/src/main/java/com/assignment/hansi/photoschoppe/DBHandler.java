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
    private static final Integer DB_VERSION = 1;
    public Context contextdb;
    private SQLiteDatabase photoSchoppeDB;


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.contextdb = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbpath = contextdb.getDatabasePath(DB_NAME).getPath();

        if (photoSchoppeDB != null && photoSchoppeDB.isOpen()) {
            return;
        }

        photoSchoppeDB = SQLiteDatabase.openDatabase(dbpath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Photographer[] getAllPGs() {
        ArrayList<Photographer> photographerList = null;
        if (photoSchoppeDB.isOpen()) {
            Cursor cursor = photoSchoppeDB.rawQuery("SELECT * FROM photographer", null);
            while (cursor.moveToNext()) {
                Photographer photographer = new Photographer(cursor.getString(0).toString(),
                        cursor.getString(1).toString(), cursor.getString(2).toString(), cursor.getString(3).toString());
                photographerList.add(photographer);
            }
            cursor.close();
        }
        photoSchoppeDB.close();
        return photographerList.toArray(new Photographer[photographerList.size()]);
    }
}
