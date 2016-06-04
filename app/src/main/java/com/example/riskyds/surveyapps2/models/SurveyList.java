package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 6/4/2016.
 */
public class SurveyList implements Serializable {
    private String idsurvey;
    private String idkeluarga;
    private String nama;
    private String tglsurvey;
    private String alamat;
    private String namadesa;
    private String namakecamatan;
    private String namakabupaten;
    private String namaprovinsi;

    public String getIdsurvey() {
        return idsurvey;
    }

    public void setIdsurvey(String idsurvey) {
        this.idsurvey = idsurvey;
    }

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

    public String getTglsurvey() {
        return tglsurvey;
    }

    public void setTglsurvey(String tglsurvey) {
        this.tglsurvey = tglsurvey;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamadesa() {
        return namadesa;
    }

    public void setNamadesa(String namadesa) {
        this.namadesa = namadesa;
    }

    public String getNamakecamatan() {
        return namakecamatan;
    }

    public void setNamakecamatan(String namakecamatan) {
        this.namakecamatan = namakecamatan;
    }
}
