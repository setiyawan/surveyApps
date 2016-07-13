package com.example.riskyds.surveyapps2.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.adapters.SurveyListAdapter;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.ArrayPair;
import com.example.riskyds.surveyapps2.models.Keluarga;
import com.example.riskyds.surveyapps2.models.Pekerjaan;
import com.example.riskyds.surveyapps2.models.Survey;
import com.example.riskyds.surveyapps2.models.SurveyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sevima on 5/31/2016.
 */
public class SurveyActivity extends AppCompatActivity implements LocationListener {

    public static final int SURVEY_CREATED = 200;
    public static final int SURVEY_CANCELLED = 400;

    LocationManager locationManager;
    String provider;
    Location location;

    private Spinner jeniskelamin, pendidikan, penguasaanbangunan, jenisatap, jenisdinding, jenislantai, airminum,
            penerangan, bahanbakarmasak, fasilitasbab, pembuangantinja, spinnerPekerjaan, spinnerKeluarga;
    ProgressBar progres_bar;
    private EditText umur, jmlhindividu;
    private Button buttonSurvey;
    ArrayPair tmp = new ArrayPair();

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        BuildVariabelList();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 20000, 1, this);
        if (location != null)
            onLocationChanged(location);

        Toast.makeText(getApplicationContext(), "Masukkan Data Survei Dengan Benar", Toast.LENGTH_SHORT).show();

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
        SessionManager sessionManager = SessionManager.getInstance(getApplicationContext());
        data2.put("idakun", sessionManager.getThisUser().getIdakun());
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

        umur = ((EditText) findViewById(R.id.umur));
        jmlhindividu = ((EditText) findViewById(R.id.jmlhindividu));

        buttonSurvey = ((Button) findViewById(R.id.btnSurvey));
        buttonSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = SessionManager.getInstance(getApplicationContext());
                final Map<String, String> data = new HashMap<>();

                Keluarga keluarga = (Keluarga) spinnerKeluarga.getSelectedItem();
                jeniskelamin = ((Spinner) findViewById(R.id.jeniskelamin));
                // Umur
                pendidikan = (Spinner) findViewById(R.id.pendidikan);
                Pekerjaan pekerjaan = (Pekerjaan) spinnerPekerjaan.getSelectedItem();
                // Jumlah Individu
                penguasaanbangunan = (Spinner) findViewById(R.id.penguasaanbangunan);
                jenisatap = ((Spinner) findViewById(R.id.jenisatap));
                jenisdinding = ((Spinner) findViewById(R.id.jenisdinding));
                jenislantai = ((Spinner) findViewById(R.id.jenislantai));
                airminum = ((Spinner) findViewById(R.id.airminum));
                penerangan = ((Spinner) findViewById(R.id.penerangan));
                bahanbakarmasak = ((Spinner) findViewById(R.id.bahanbakarmasak));
                fasilitasbab = ((Spinner) findViewById(R.id.fasilitasbab));
                pembuangantinja = ((Spinner) findViewById(R.id.pembuangantinja));

                if (umur.getText().toString().equals("") || jmlhindividu.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Tidak Boleh Ada Informasi Yang Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    data.put("idakun", sessionManager.getThisUser().getIdakun());
                    data.put("idkeluarga", keluarga.getIdkeluarga());

                    tmp = Survey.getJenisKelamin();
                    data.put("jeniskelamin", tmp.getValueFromKey(jeniskelamin.getSelectedItem().toString()));
                    data.put("umur", umur.getText().toString());
                    tmp = Survey.getPendidikan();
                    data.put("pendidikan", tmp.getValueFromKey(pendidikan.getSelectedItem().toString()));
                    data.put("pekerjaan", pekerjaan.getIdgaji());
                    data.put("jmlhindividu", jmlhindividu.getText().toString());
                    tmp = Survey.getPenguasaanBangunan();
                    data.put("penguasaanbangunan", tmp.getValueFromKey(penguasaanbangunan.getSelectedItem().toString()));
                    tmp = Survey.getJenisAtap();
                    data.put("jenisatap", tmp.getValueFromKey(jenisatap.getSelectedItem().toString()));
                    tmp = Survey.getJenisDinding();
                    data.put("jenisdinding", tmp.getValueFromKey(jenisdinding.getSelectedItem().toString()));
                    tmp = Survey.getJenisLantai();
                    data.put("jenislantai", tmp.getValueFromKey(jenislantai.getSelectedItem().toString()));
                    tmp = Survey.getAirMinum();
                    data.put("airminum", tmp.getValueFromKey(airminum.getSelectedItem().toString()));
                    tmp = Survey.getPenerangan();
                    data.put("penerangan", tmp.getValueFromKey(penerangan.getSelectedItem().toString()));
                    tmp = Survey.getBahanBakarMasak();
                    data.put("bahanbakarmasak", tmp.getValueFromKey(bahanbakarmasak.getSelectedItem().toString()));
                    tmp = Survey.getFasilitasBab();
                    data.put("fasilitasbab", tmp.getValueFromKey(fasilitasbab.getSelectedItem().toString()));
                    tmp = Survey.getPembuanganTinja();
                    data.put("pembuangantinja", tmp.getValueFromKey(pembuangantinja.getSelectedItem().toString()));

                    data.put("lattitude", String.valueOf(location.getLatitude()));
                    data.put("longitude", String.valueOf(location.getLongitude()));

                    AlertDialog alertDialog = new AlertDialog.Builder(SurveyActivity.this).create();
                    alertDialog.setTitle("Peringatan!");
                    alertDialog.setMessage("Apakah Data Yang Anda Isi Benar? Pastikan Anda Sudah Melakukan Konfirmasi Ulang.");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Simpan",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    RequestAsyncTask add = new RequestAsyncTask(data, null, progres_bar) {
                                        @Override
                                        protected void setAfterThread(ResponseManager responseManager) {
                                            if (responseManager.getCode().equals(Url.CodeTrue)) {
                                                setResult(SURVEY_CREATED);
                                                finish();
                                            }
                                            Toast.makeText(getApplicationContext(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    };
                                    add.execute(Url.AddSurvey);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Konfirmasi Ulang",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    //SPINNER STATIC
    public void BuildVariabelList() {
        tmp = Survey.getJenisKelamin();
        List<String> JenisKelamin = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            JenisKelamin.add(tmp.getData().get(i).getKey());
        }
        jeniskelamin = (Spinner) findViewById(R.id.jeniskelamin);
        ArrayAdapter<String> jkAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisKelamin);
        jeniskelamin.setAdapter(jkAdapter);


        tmp = Survey.getPendidikan();
        List<String> Pendidikan = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            Pendidikan.add(tmp.getData().get(i).getKey());
        }
        pendidikan = (Spinner) findViewById(R.id.pendidikan);
        ArrayAdapter<String> pdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Pendidikan);
        pendidikan.setAdapter(pdAdapter);

        tmp = Survey.getPenguasaanBangunan();
        List<String> PenguasaanBangunan = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            PenguasaanBangunan.add(tmp.getData().get(i).getKey());
        }
        penguasaanbangunan = (Spinner) findViewById(R.id.penguasaanbangunan);
        ArrayAdapter<String> pbAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PenguasaanBangunan);
        penguasaanbangunan.setAdapter(pbAdapter);

        tmp = Survey.getJenisAtap();
        List<String> JenisAtap = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            JenisAtap.add(tmp.getData().get(i).getKey());
        }
        jenisatap = (Spinner) findViewById(R.id.jenisatap);
        ArrayAdapter<String> jaAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisAtap);
        jenisatap.setAdapter(jaAdapter);

        tmp = Survey.getJenisDinding();
        List<String> JenisDinding = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            JenisDinding.add(tmp.getData().get(i).getKey());
        }
        jenisdinding = (Spinner) findViewById(R.id.jenisdinding);
        ArrayAdapter<String> jdAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisDinding);
        jenisdinding.setAdapter(jdAdapter);

        tmp = Survey.getJenisLantai();
        List<String> JenisLantai = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            JenisLantai.add(tmp.getData().get(i).getKey());
        }
        jenislantai = (Spinner) findViewById(R.id.jenislantai);
        ArrayAdapter<String> jlAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, JenisLantai);
        jenislantai.setAdapter(jlAdapter);

        tmp = Survey.getAirMinum();
        List<String> AirMinum = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            AirMinum.add(tmp.getData().get(i).getKey());
        }
        airminum = (Spinner) findViewById(R.id.airminum);
        ArrayAdapter<String> amAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, AirMinum);
        airminum.setAdapter(amAdapter);

        tmp = Survey.getPenerangan();
        List<String> Penerangan = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            Penerangan.add(tmp.getData().get(i).getKey());
        }
        penerangan = (Spinner) findViewById(R.id.penerangan);
        ArrayAdapter<String> pgAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Penerangan);
        penerangan.setAdapter(pgAdapter);

        tmp = Survey.getBahanBakarMasak();
        List<String> BahanBakar = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            BahanBakar.add(tmp.getData().get(i).getKey());
        }
        bahanbakarmasak = (Spinner) findViewById(R.id.bahanbakarmasak);
        ArrayAdapter<String> bbAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, BahanBakar);
        bahanbakarmasak.setAdapter(bbAdapter);

        tmp = Survey.getFasilitasBab();
        List<String> FasilitasBab = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            FasilitasBab.add(tmp.getData().get(i).getKey());
        }
        fasilitasbab = (Spinner) findViewById(R.id.fasilitasbab);
        ArrayAdapter<String> faAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, FasilitasBab);
        fasilitasbab.setAdapter(faAdapter);

        tmp = Survey.getPembuanganTinja();
        List<String> PembuanganTinja = new ArrayList<>();
        for (int i = 0; i < tmp.getData().size(); i++) {
            PembuanganTinja.add(tmp.getData().get(i).getKey());
        }
        pembuangantinja = (Spinner) findViewById(R.id.pembuangantinja);
        ArrayAdapter<String> ptAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PembuanganTinja);
        pembuangantinja.setAdapter(ptAdapter);
    }

    @Override
    public void onBackPressed() {
        setResult(SURVEY_CANCELLED);
        super.onBackPressed();
    }
}
