package com.example.riskyds.surveyapps2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.Survey;
import com.example.riskyds.surveyapps2.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sevima on 5/31/2016.
 */
public class SurveyActivity extends AppCompatActivity {
    private Spinner jeniskelamin, pendidikan, penguasaanbangunan, jenisatap, jenisdinding, jenislantai, airminum,
            penerangan, bahanbakarmasak, fasilitasbab, pembuangantinja;
    ProgressBar progres_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        BuildVariabelList();

//        Mengisi SPINNER PEKERJAAN
//        Map<String, String> data = new HashMap<>();
//        RequestAsyncTask listPekerjaan = new RequestAsyncTask(data, null, progres_bar) {
//            @Override
//            protected void setAfterThread(ResponseManager responseManager) {
//                responseManager.getMany()
//            }
//        };
//        listPekerjaan.execute(Url.SektorPekerjaan);
    }

    //SPINNER STATIC
    public void BuildVariabelList() {
        List<String> JenisKelamin = Survey.getJenisKelamin();
        jeniskelamin = (Spinner) findViewById(R.id.jeniskelamin);
        ArrayAdapter<String> jkAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisKelamin);
        jeniskelamin.setAdapter(jkAdapter);

        List<String> Pendidikan = Survey.getPendidikan();
        pendidikan = (Spinner) findViewById(R.id.pendidikan);
        ArrayAdapter<String> pdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Pendidikan);
        pendidikan.setAdapter(pdAdapter);

        List<String> PenguasaanBangunan = Survey.getPenguasaanBangunan();
        penguasaanbangunan = (Spinner) findViewById(R.id.penguasaanbangunan);
        ArrayAdapter<String> pbAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PenguasaanBangunan);
        penguasaanbangunan.setAdapter(pbAdapter);

        List<String> JenisAtap = Survey.getJenisAtap();
        jenisatap = (Spinner) findViewById(R.id.jenisatap);
        ArrayAdapter<String> jaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisAtap);
        jenisatap.setAdapter(jaAdapter);

        List<String> JenisDinding = Survey.getJenisDinding();
        jenisdinding = (Spinner) findViewById(R.id.jenisdinding);
        ArrayAdapter<String> jdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisDinding);
        jenisdinding.setAdapter(jdAdapter);

        List<String> JenisLantai = Survey.getJenisLantai();
        jenislantai = (Spinner) findViewById(R.id.jenislantai);
        ArrayAdapter<String> jlAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisLantai);
        jenislantai.setAdapter(jlAdapter);

        List<String> AirMinum = Survey.getAirMinum();
        airminum = (Spinner) findViewById(R.id.airminum);
        ArrayAdapter<String> amAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, AirMinum);
        airminum.setAdapter(amAdapter);

        List<String> Penerangan = Survey.getPenerangan();
        penerangan = (Spinner) findViewById(R.id.penerangan);
        ArrayAdapter<String> pgAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Penerangan);
        penerangan.setAdapter(pgAdapter);

        List<String> BahanBakar = Survey.getBahanBakarMasak();
        bahanbakarmasak = (Spinner) findViewById(R.id.bahanbakarmasak);
        ArrayAdapter<String> bmAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, BahanBakar);
        bahanbakarmasak.setAdapter(bmAdapter);

        List<String> FasilitasBab = Survey.getFasilitasBab();
        fasilitasbab = (Spinner) findViewById(R.id.fasilitasbab);
        ArrayAdapter<String> faAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, FasilitasBab);
        fasilitasbab.setAdapter(faAdapter);

        List<String> PembuanganTinja = Survey.getPembuanganTinja();
        pembuangantinja = (Spinner) findViewById(R.id.pembuangantinja);
        ArrayAdapter<String> ptAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PembuanganTinja);
        pembuangantinja.setAdapter(ptAdapter);
    }
}
