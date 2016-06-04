package com.example.riskyds.surveyapps2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.riskyds.surveyapps2.models.ArrayPair;
import com.example.riskyds.surveyapps2.models.Pair;
import com.example.riskyds.surveyapps2.models.Pekerjaan;
import com.example.riskyds.surveyapps2.models.Survey;
import com.example.riskyds.surveyapps2.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sevima on 5/31/2016.
 */
public class SurveyActivity extends AppCompatActivity {
    private Spinner jeniskelamin, pendidikan, penguasaanbangunan, jenisatap, jenisdinding, jenislantai, airminum,
            penerangan, bahanbakarmasak, fasilitasbab, pembuangantinja, spinnerPekerjaan;
    ProgressBar progres_bar;
    ArrayPair tmp = new ArrayPair();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        BuildVariabelList();

//        Mengisi SPINNER PEKERJAAN
        Map<String, String> data = new HashMap<>();
        RequestAsyncTask listPekerjaan = new RequestAsyncTask(data, null, progres_bar) {
            @Override
            protected void setAfterThread(ResponseManager responseManager) {
                List<Pekerjaan> pekerjaans = (List<Pekerjaan>) responseManager.getMany(Pekerjaan.class);
                spinnerPekerjaan = ((Spinner) findViewById(R.id.pekerjaan));
                ArrayAdapter<Pekerjaan> pbAdapter = new ArrayAdapter<>
                        (SurveyActivity.this, android.R.layout.simple_spinner_dropdown_item, pekerjaans);
                spinnerPekerjaan.setAdapter(pbAdapter);
            }
        };
        listPekerjaan.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Url.SektorPekerjaan);

//        Mengirim id
//        Pekerjaan p = (Pekerjaan) spinnerPekerjaan.getSelectedItem();
//        p.getIdgaji();
    }

    //SPINNER STATIC
    public void BuildVariabelList() {
        List<String> JenisKelamin = Survey.getJenisKelamin();
        jeniskelamin = (Spinner) findViewById(R.id.jeniskelamin);
        ArrayAdapter<String> jkAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisKelamin);
        jeniskelamin.setAdapter(jkAdapter);


        tmp = Survey.getPendidikan();
        List<String> Pendidikan= new ArrayList<>();

        for (int i=0; i<tmp.getData().size();i++) {
            Pendidikan.add(tmp.getData().get(i).getKey());
        }

        pendidikan = (Spinner) findViewById(R.id.pendidikan);

        pendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("RESULT", tmp.getValueFromKey(pendidikan.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
