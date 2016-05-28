package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 5/28/2016.
 */
public class Password implements Serializable {
    private String password;
    private String password1;
    private String password2;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
