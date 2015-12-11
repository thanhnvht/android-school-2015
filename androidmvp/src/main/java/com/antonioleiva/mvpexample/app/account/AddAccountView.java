package com.antonioleiva.mvpexample.app.account;

/**
 * Created by thanhnguyen on 11/12/2015.
 */
public interface AddAccountView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void setEmailError();
}
