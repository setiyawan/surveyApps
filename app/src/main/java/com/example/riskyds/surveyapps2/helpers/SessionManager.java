package com.example.riskyds.surveyapps2.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.models.User;

/**
 * Created by stef_ang on 12/21/2015.
 */
public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "Survey Apps2";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public static SessionManager getInstance(Context context) {
        return new SessionManager(context);
    }

    public void init(User user) {
        // TODO nanti diisi status dan jabatannya user juga
        editor.putString("idakun", user.getIdakun());
        editor.putString("nama", user.getNama());
        editor.putString("username", user.getUsername());
        editor.putString("jabatan", user.getJabatan());
        editor.putString("nohp", user.getNohp());
        editor.putString("file", user.getFile());
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    public User getThisUser() {
        User user = new User();

        user.setIdakun(sharedPreferences.getString("idakun", null));
        user.setNama(sharedPreferences.getString("nama", null));
        user.setUsername(sharedPreferences.getString("username", null));
        user.setJabatan(sharedPreferences.getString("jabatan", null));
        user.setNohp(sharedPreferences.getString("nohp", null));
        user.setFile(sharedPreferences.getString("file", null));

        return user;
    }

    public void destroy() {
        editor.clear();
        editor.commit();
    }
}
