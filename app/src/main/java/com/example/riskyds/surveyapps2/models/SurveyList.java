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
    private String jeniskelamin, umur, pendidikan, pekerjaan, jmlhindividu;
    private String penguasaanbangunan, jenisatap, jenisdinding, jenislantai, airminum, penerangan, bahanbakarmasak, fasilitasbab, pembuangantinja;
    private String isvalid;

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

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

    public String getNamakabupaten() {
        return namakabupaten;
    }

    public void setNamakabupaten(String namakabupaten) {
        this.namakabupaten = namakabupaten;
    }

    public String getNamaprovinsi() {
        return namaprovinsi;
    }

    public void setNamaprovinsi(String namaprovinsi) {
        this.namaprovinsi = namaprovinsi;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getJmlhindividu() {
        return jmlhindividu;
    }

    public void setJmlhindividu(String jmlhindividu) {
        this.jmlhindividu = jmlhindividu;
    }

    public String getPenguasaanbangunan() {
        return penguasaanbangunan;
    }

    public void setPenguasaanbangunan(String penguasaanbangunan) {
        this.penguasaanbangunan = penguasaanbangunan;
    }

    public String getJenisatap() {
        return jenisatap;
    }

    public void setJenisatap(String jenisatap) {
        this.jenisatap = jenisatap;
    }

    public String getJenisdinding() {
        return jenisdinding;
    }

    public void setJenisdinding(String jenisdinding) {
        this.jenisdinding = jenisdinding;
    }

    public String getJenislantai() {
        return jenislantai;
    }

    public void setJenislantai(String jenislantai) {
        this.jenislantai = jenislantai;
    }

    public String getAirminum() {
        return airminum;
    }

    public void setAirminum(String airminum) {
        this.airminum = airminum;
    }

    public String getPenerangan() {
        return penerangan;
    }

    public void setPenerangan(String penerangan) {
        this.penerangan = penerangan;
    }

    public String getBahanbakarmasak() {
        return bahanbakarmasak;
    }

    public void setBahanbakarmasak(String bahanbakarmasak) {
        this.bahanbakarmasak = bahanbakarmasak;
    }

    public String getFasilitasbab() {
        return fasilitasbab;
    }

    public void setFasilitasbab(String fasilitasbab) {
        this.fasilitasbab = fasilitasbab;
    }

    public String getPembuangantinja() {
        return pembuangantinja;
    }

    public void setPembuangantinja(String pembuangantinja) {
        this.pembuangantinja = pembuangantinja;
    }
}
