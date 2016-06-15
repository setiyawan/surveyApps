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

    public static ArrayPair getJenisKelamin() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Laki-laki", "1"));
        list.getData().add(new Pair("Perempuan", "2"));
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

    public static ArrayPair getPenguasaanBangunan() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Milik sendiri", "1"));
        list.getData().add(new Pair("Kontrak/Sewa", "2"));
        list.getData().add(new Pair("Lainnya", "3"));
        return list;
    }

    public static ArrayPair getJenisAtap() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Beton", "1"));
        list.getData().add(new Pair("Genteng", "2"));
        list.getData().add(new Pair("Sirap", "3"));
        list.getData().add(new Pair("Seng", "4"));
        list.getData().add(new Pair("Asbes", "5"));
        list.getData().add(new Pair("Ijuk/Rumbai", "6"));
        return list;
    }

    public static ArrayPair getJenisDinding() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Tembok", "1"));
        list.getData().add(new Pair("Kayu", "2"));
        list.getData().add(new Pair("Bambu", "3"));
        list.getData().add(new Pair("Lainnya", "4"));
        return list;
    }

    public static ArrayPair getJenisLantai() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Bukan Tanah/bambu", "1"));
        list.getData().add(new Pair("Tanah", "2"));
        list.getData().add(new Pair("Bambu", "3"));
        return list;
    }

    public static ArrayPair getAirMinum() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Air Kemasan", "1"));
        list.getData().add(new Pair("Air Ledeng", "2"));
        list.getData().add(new Pair("Air Terlindung", "3"));
        list.getData().add(new Pair("Air Tidak Terlindung", "4"));
        return list;
    }

    public static ArrayPair getPenerangan() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Listrik PLN", "1"));
        list.getData().add(new Pair("Listrik Non PLN", "2"));
        list.getData().add(new Pair("Tidak Ada Listrik", "3"));
        return list;
    }

    public static ArrayPair getBahanBakarMasak() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Listrik/Gas/LP", "1"));
        list.getData().add(new Pair("Lainnya", "2"));
        return list;
    }

    public static ArrayPair getFasilitasBab() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Sendiri", "1"));
        list.getData().add(new Pair("Bersama/Umum", "2"));
        list.getData().add(new Pair("Tidak Ada", "3"));
        return list;
    }

    public static ArrayPair getPembuanganTinja() {
        ArrayPair list = new ArrayPair();
        list.getData().add(new Pair("Tangki/SPAL", "1"));
        list.getData().add(new Pair("Lainnya", "2"));
        return list;
    }
}
