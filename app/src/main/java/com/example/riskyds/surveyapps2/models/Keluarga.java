package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 6/15/2016.
 */
public class Keluarga implements Serializable {
    private String idkeluarga;
    private String nama;
    private String alamat;

    public String getIdkeluarga() {
        return idkeluarga;
    }

    public void setIdkeluarga(String idkeluarga) {
        this.idkeluarga = idkeluarga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return getNama() + " - " + getAlamat();
    }
}
