package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;

/**
 * Created by sevima on 5/27/2016.
 */
public class Dashboard implements Serializable {

    private String idprovinsi;
    private String idkabupaten;
    private String idkecamatan;
    private String iddesa;
    private String datatarget;
    private String datamasuk;
    private String datavalid;
    private String datainvalid;

    public String getIdprovinsi() {
        return idprovinsi;
    }

    public void setIdprovinsi(String idprovinsi) {
        this.idprovinsi = idprovinsi;
    }

    public String getIdkabupaten() {
        return idkabupaten;
    }

    public void setIdkabupaten(String idkabupaten) {
        this.idkabupaten = idkabupaten;
    }

    public String getIdkecamatan() {
        return idkecamatan;
    }

    public void setIdkecamatan(String idkecamatan) {
        this.idkecamatan = idkecamatan;
    }

    public String getIddesa() {
        return iddesa;
    }

    public void setIddesa(String iddesa) {
        this.iddesa = iddesa;
    }

    public String getDataTarget() {
        return datatarget;
    }

    public void setDataTarget(String dataTarget) {
        this.datatarget = dataTarget;
    }

    public String getDataMasuk() {
        return datamasuk;
    }

    public void setDataMasuk(String dataMasuk) {
        this.datamasuk = dataMasuk;
    }

    public String getDataValid() {
        return datavalid;
    }

    public void setDataValid(String dataValid) {
        this.datavalid = dataValid;
    }

    public String getDataInvalid() {
        return datainvalid;
    }

    public void setDataInvalid(String dataInvalid) {
        this.datainvalid = dataInvalid;
    }
}
