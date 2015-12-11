package com.antonioleiva.mvpexample.app.account;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.data.Constants;
import com.antonioleiva.mvpexample.app.data.MyDB;

import java.util.ArrayList;
import java.util.List;

public class AddAccountActivity extends Activity implements AddAccountView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private EditText email;
    private Button submit;

    private AddAccountPresenter presenter;
    private MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        progressBar = (ProgressBar) findViewById(R.id.addAccountProgress);
        username = (EditText) findViewById(R.id.addAccountNameET);
        password = (EditText) findViewById(R.id.addAccountPasswordET);
        email = (EditText) findViewById(R.id.addAccountEmailET);
        progressBar = (ProgressBar) findViewById(R.id.addAccountProgress);

        ((Button) findViewById(R.id.addAccountSubmitBT)).setOnClickListener(this);

        presenter = new AddAccountPresenterImpl(this);
        myDB = new MyDB(this);
        myDB.open();


        getData();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void setEmailError() {
        email.setError(getString(R.string.email_error));
    }

    @Override
    public void onClick(View v) {
        presenter.addAccount(username.getText().toString(), password.getText().toString(), email.getText().toString());
    }

    public void getData() {
        List<Account> accounts = new ArrayList<Account>();
        Account temp;
        Cursor c = myDB.getAccounts();
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(Constants.ACCOUNT_NAME));
                String pass = c.getString(c.getColumnIndex(Constants.ACCOUNT_PASS));
                String email = c.getString(c.getColumnIndex(Constants.ACCOUNT_EMAIL));

                temp = new Account(name, pass, email);
                accounts.add(temp);
            } while (c.moveToNext());
        }
    }

    private class Account {
        public Account(String name, String pass, String email) {
            this.name = name;
            this.pass = pass;
            this.email = email;
        }

        public String name;
        public String pass;
        public String email;
    }
}
