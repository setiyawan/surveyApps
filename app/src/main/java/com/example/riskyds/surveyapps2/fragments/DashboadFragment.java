package com.example.riskyds.surveyapps2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.Dashboard;

import java.util.HashMap;
import java.util.Map;

public class DashboadFragment extends Fragment {

    protected View v;
    protected float prosentase;
    protected int dtMasuk, dtTarget;

    protected TextView idprovinsi;
    protected TextView idkabupaten;
    protected TextView idkecamatan;
    protected TextView iddesa;
    protected TextView datatarget;
    protected TextView datamasuk;
    protected TextView datavalid;
    protected TextView dataInvalid;
    protected TextView dataProsentase;

    ProgressBar progres_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboad, container, false);
        idprovinsi = ((TextView) v.findViewById(R.id.idprovinsi));
        idkabupaten = ((TextView) v.findViewById(R.id.idkabupaten));
        idkecamatan = ((TextView) v.findViewById(R.id.idkecamatan));
        iddesa = ((TextView) v.findViewById(R.id.iddesa));

        datatarget = ((TextView) v.findViewById(R.id.dataTarget));
        datamasuk = ((TextView) v.findViewById(R.id.dataMasuk));
        datavalid = ((TextView) v.findViewById(R.id.dataValid));
        dataInvalid = ((TextView) v.findViewById(R.id.dataInvalid));

        dataProsentase = ((TextView) v.findViewById(R.id.prosentase));

        progres_bar = ((ProgressBar) v.findViewById(R.id.progress_bar));

        SessionManager sessionManager = SessionManager.getInstance(getContext());
        Map<String, String> data = new HashMap<>();
        data.put("idakun", sessionManager.getThisUser().getIdakun());
        RequestAsyncTask login = new RequestAsyncTask(data, null, progres_bar) {
            @Override
            protected void setAfterThread(ResponseManager responseManager) {
                Dashboard dashboard = ((Dashboard) responseManager.getOne(Dashboard.class));
                idprovinsi.setText(dashboard.getIdprovinsi());
                idkabupaten.setText(dashboard.getIdkabupaten());
                idkecamatan.setText(dashboard.getIdkecamatan());
                iddesa.setText(dashboard.getIddesa());

                datatarget.setText(dashboard.getDataTarget());
                datamasuk.setText(dashboard.getDataMasuk());
                datavalid.setText(dashboard.getDataValid());
                dataInvalid.setText(dashboard.getDataInvalid());

                dtMasuk = Integer.parseInt(dashboard.getDataMasuk());
                dtMasuk = dtMasuk * 100;
                dtTarget = Integer.parseInt(dashboard.getDataTarget());
                prosentase = (float) dtMasuk/dtTarget;
                dataProsentase.setText(String.format("%.2f", prosentase) + " %");

                Toast.makeText(getActivity(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        login.execute(Url.Home);
        return v;
    }

}
