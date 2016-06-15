package com.example.riskyds.surveyapps2.models;

import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sevima on 5/31/2016.
 */
public class Survey implements Serializable {
    private int idakun;
    private int idkeluarga;
    private int jeniskelamin;

    public static List<String> getJenisKelamin() {
        List<String> list = new ArrayList<>();
        list.add("Laki-laki");
        list.add("Perempuan");
        return list;
    }

    public static ArrayPair getPendidikan() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Perguruan Tinggi", "4"));
        list.getData().add(new Pair("SMA/sederajat", "3"));
        list.getData().add(new Pair("SMP/sederajat", "2"));
        list.getData().add(new Pair("SD/sederajat", "1"));
        list.getData().add(new Pair("Tidak punya ijazah", "0"));
        return list;
    }

    public static List<String> getPenguasaanBangunan() {
        List<String> list = new ArrayList<>();
        list.add("Milik sendiri");
        list.add("Kontrak/Sewa");
        list.add("Lainnya");
        return list;
    }

    public static List<String> getJenisAtap() {
        List<String> list = new ArrayList<>();
        list.add("Beton");
        list.add("Genteng");
        list.add("Sirap");
        list.add("Seng");
        list.add("Asbes");
        list.add("Ijuk/Rumbai");
        return list;
    }

    public static List<String> getJenisDinding() {
        List<String> list = new ArrayList<>();
        list.add("Tembok");
        list.add("Kayu");
        list.add("Bambu");
        list.add("Lainnya");
        return list;
    }

    public static List<String> getJenisLantai() {
        List<String> list = new ArrayList<>();
        list.add("Bukan Tanah/bambu");
        list.add("Tanah");
        list.add("Bambu");
        return list;
    }

    public static List<String> getAirMinum() {
        List<String> list = new ArrayList<>();
        list.add("Air Kemasan");
        list.add("Air Ledeng");
        list.add("Air Terlindung");
        list.add("Air idak Terlindung");
        return list;
    }

    public static List<String> getPenerangan() {
        List<String> list = new ArrayList<>();
        list.add("Listrik PLN");
        list.add("Listrik Non PLN");
        list.add("Tidak Ada Listrik");
        return list;
    }

    public static List<String> getBahanBakarMasak() {
        List<String> list = new ArrayList<>();
        list.add("Listrik/Gas/LPJ");
        list.add("Lainnya");
        return list;
    }

    public static List<String> getFasilitasBab() {
        List<String> list = new ArrayList<>();
        list.add("Sendiri");
        list.add("Bersama/Umum");
        list.add("Tidak Ada");
        return list;
    }

    public static List<String> getPembuanganTinja() {
        List<String> list = new ArrayList<>();
        list.add("Tangki/SPAL");
        list.add("Lainnya");
        return list;
    }
}
