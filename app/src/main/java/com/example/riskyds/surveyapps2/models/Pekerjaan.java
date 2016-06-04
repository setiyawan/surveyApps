package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 6/4/2016.
 */
public class Pekerjaan implements Serializable {
    private String idgaji;
    private String sektorpekerjaan;
    private String nominal;

    public String getIdgaji() {
        return idgaji;
    }

    public void setIdgaji(String idgaji) {
        this.idgaji = idgaji;
    }

    public String getSektorpekerjaan() {
        return sektorpekerjaan;
    }

    public void setSektorpekerjaan(String sektorpekerjaan) {
        this.sektorpekerjaan = sektorpekerjaan;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    @Override
    public String toString() {
        return getSektorpekerjaan() + " - " + getNominal();
    }
}
