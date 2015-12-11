package com.antonioleiva.mvpexample.app.data;

/**
 * Created by thanhnguyen on 11/12/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class MyDB {
    private SQLiteDatabase db;
    private final Context context;
    private final MyDBhelper dbhelper;

    public MyDB(Context c) {
        context = c;
        dbhelper = new MyDBhelper(context, Constants.DATABASE_NAME, null,
                Constants.DATABASE_VERSION);
    }

    public void close() {
        db.close();
    }

    public void open() throws SQLiteException {
        try {
            db = dbhelper.getWritableDatabase();
            String path = db.getPath();
        } catch (SQLiteException ex) {
            Log.v("Open database exception caught", ex.getMessage());
            db = dbhelper.getReadableDatabase();
        }
//        SQLiteDatabase: /data/data/com.example.thanhnguyen.androidmvp/databases/datastorage
    }

    public long insertAccount(String title, String content, String email) {
        try {
            ContentValues newTaskValue = new ContentValues();
            newTaskValue.put(Constants.ACCOUNT_NAME, title);
            newTaskValue.put(Constants.ACCOUNT_PASS, content);
            newTaskValue.put(Constants.ACCOUNT_EMAIL, email);
            return db.insert(Constants.TABLE_NAME, null, newTaskValue);
        } catch (SQLiteException ex) {
            Log.v("Insert into database exception caught", ex.getMessage());
            return -1;
        } finally {
            db.close();
        }
    }

    public Cursor getAccounts() {
        Cursor c = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        return c;
    }
}