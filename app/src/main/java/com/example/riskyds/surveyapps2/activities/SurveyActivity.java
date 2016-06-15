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
import com.example.riskyds.surveyapps2.models.Keluarga;
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
            penerangan, bahanbakarmasak, fasilitasbab, pembuangantinja, spinnerPekerjaan, spinnerKeluarga;
    ProgressBar progres_bar;
    private Button buttonSurvey;
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

        Map<String, String> data2 = new HashMap<>();
        RequestAsyncTask listSurvey = new RequestAsyncTask(data2, null, progres_bar) {
            @Override
            protected void setAfterThread(ResponseManager responseManager) {
                List<Keluarga> keluargas = (List<Keluarga>) responseManager.getMany(Keluarga.class);
                spinnerKeluarga = ((Spinner) findViewById(R.id.idkeluargas));
                ArrayAdapter<Keluarga> pbAdapter = new ArrayAdapter<>
                        (SurveyActivity.this, android.R.layout.simple_spinner_dropdown_item, keluargas);
                spinnerKeluarga.setAdapter(pbAdapter);
            }
        };
        listSurvey.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Url.ListSurvey);

        buttonSurvey = ((Button) findViewById(R.id.btnSurvey));
        buttonSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Keluarga keluarga = (Keluarga) spinnerKeluarga.getSelectedItem();
                Pekerjaan pekerjaan = (Pekerjaan) spinnerPekerjaan.getSelectedItem();
                pendidikan = (Spinner) findViewById(R.id.pendidikan);
                penguasaanbangunan = (Spinner) findViewById(R.id.penguasaanbangunan);

                Log.v("ID Keluarga ", keluarga.getIdkeluarga());
                Log.v("ID Pekerjaan ", pekerjaan.getIdgaji());

                tmp = Survey.getPendidikan();
                Log.v("ID Pendidikan ", tmp.getValueFromKey(pendidikan.getSelectedItem().toString()));

                tmp = Survey.getPenguasaanBangunan();
                Log.v("ID Penguasaan Bangunan ", tmp.getValueFromKey(penguasaanbangunan.getSelectedItem().toString()));
            }
        });
    }

    //SPINNER STATIC
    public void BuildVariabelList() {
        tmp = Survey.getJenisKelamin();
        List<String> JenisKelamin = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            JenisKelamin.add(tmp.getData().get(i).getKey());
        }
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
        ArrayAdapter<String> pdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Pendidikan);
        pendidikan.setAdapter(pdAdapter);

        tmp = Survey.getPenguasaanBangunan();
        List<String> PenguasaanBangunan= new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            PenguasaanBangunan.add(tmp.getData().get(i).getKey());
        }
        penguasaanbangunan = (Spinner) findViewById(R.id.penguasaanbangunan);
        ArrayAdapter<String> pbAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PenguasaanBangunan);
        penguasaanbangunan.setAdapter(pbAdapter);

        tmp = Survey.getJenisAtap();
        List<String> JenisAtap = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            JenisAtap.add(tmp.getData().get(i).getKey());
        }
        jenisatap = (Spinner) findViewById(R.id.jenisatap);
        ArrayAdapter<String> jaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisAtap);
        jenisatap.setAdapter(jaAdapter);

        tmp = Survey.getJenisDinding();
        List<String> JenisDinding = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            JenisDinding.add(tmp.getData().get(i).getKey());
        }
        jenisdinding = (Spinner) findViewById(R.id.jenisdinding);
        ArrayAdapter<String> jdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisDinding);
        jenisdinding.setAdapter(jdAdapter);

        tmp = Survey.getJenisLantai();
        List<String> JenisLantai = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            JenisLantai.add(tmp.getData().get(i).getKey());
        }
        jenislantai = (Spinner) findViewById(R.id.jenislantai);
        ArrayAdapter<String> jlAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisLantai);
        jenislantai.setAdapter(jlAdapter);

        tmp = Survey.getAirMinum();
        List<String> AirMinum = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            AirMinum.add(tmp.getData().get(i).getKey());
        }
        airminum = (Spinner) findViewById(R.id.airminum);
        ArrayAdapter<String> amAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, AirMinum);
        airminum.setAdapter(amAdapter);

        tmp = Survey.getPenerangan();
        List<String> Penerangan = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            Penerangan.add(tmp.getData().get(i).getKey());
        }
        penerangan = (Spinner) findViewById(R.id.penerangan);
        ArrayAdapter<String> pgAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Penerangan);
        penerangan.setAdapter(pgAdapter);

        tmp = Survey.getBahanBakarMasak();
        List<String> BahanBakar = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            BahanBakar.add(tmp.getData().get(i).getKey());
        }
        bahanbakarmasak = (Spinner) findViewById(R.id.bahanbakarmasak);
        ArrayAdapter<String> bbAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, BahanBakar);
        bahanbakarmasak.setAdapter(bbAdapter);

        tmp = Survey.getFasilitasBab();
        List<String> FasilitasBab = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            FasilitasBab.add(tmp.getData().get(i).getKey());
        }
        fasilitasbab = (Spinner) findViewById(R.id.fasilitasbab);
        ArrayAdapter<String> faAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, FasilitasBab);
        fasilitasbab.setAdapter(faAdapter);

        tmp = Survey.getPembuanganTinja();
        List<String> PembuanganTinja = new ArrayList<>();
        for (int i=0; i<tmp.getData().size();i++) {
            PembuanganTinja.add(tmp.getData().get(i).getKey());
        }
        pembuangantinja = (Spinner) findViewById(R.id.pembuangantinja);
        ArrayAdapter<String> ptAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PembuanganTinja);
        pembuangantinja.setAdapter(ptAdapter);
    }
}
