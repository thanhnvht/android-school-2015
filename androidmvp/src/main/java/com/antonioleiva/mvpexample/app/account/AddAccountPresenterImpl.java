package com.antonioleiva.mvpexample.app.account;

import android.app.Activity;
import android.util.Log;

import com.antonioleiva.mvpexample.app.data.MyDB;

/**
 * Created by thanhnguyen on 11/12/2015.
 */
public class  AddAccountPresenterImpl implements AddAccountPresenter {

    private Activity activity;
    private MyDB myDB;

    public AddAccountPresenterImpl(Activity activity) {
        this.activity = activity;
    }

    public void addAccount(String username, String password, String email) {
        long results = getMyDB().insertAccount(username, password, email);
        Log.v("Insert a Account into database. Result = "+results, "");
    }

    private MyDB getMyDB() {
        if (myDB == null) {
            myDB = new MyDB(activity);
            myDB.open();
        }

        return myDB;
    }
}
