package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 5/27/2016.
 */
public class User implements Serializable {
    private String idakun;
    private String username;
    private String nama;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdakun() {
        return idakun;
    }

    public void setIdakun(String idakun) {
        this.idakun = idakun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}