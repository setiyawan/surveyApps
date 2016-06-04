package com.example.riskyds.surveyapps2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.activities.LoginActivity;
import com.example.riskyds.surveyapps2.activities.SurveyActivity;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sevima on 6/1/2016.
 */
public class SurveyFragment extends Fragment {
    protected View v;

    ProgressBar progres_bar;
    Button btnSurvey;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_survey, container, false);

        progres_bar = ((ProgressBar) v.findViewById(R.id.progress_bar));

        btnSurvey =((Button) v.findViewById(R.id.btnSurvey));
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = SessionManager.getInstance(getContext());
                Map<String, String> data = new HashMap<>();
                data.put("idakun", sessionManager.getThisUser().getIdakun());

                // request
                RequestAsyncTask login = new RequestAsyncTask(data, null, progres_bar) {
                    @Override
                    protected void setAfterThread(ResponseManager responseManager) {
                        Toast.makeText(getActivity(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                login.execute(Url.SurveyAll);
            }
        });
        return v;
    }
}
