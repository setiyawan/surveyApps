package com.example.riskyds.surveyapps2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.activities.DrawerActivity;
import com.example.riskyds.surveyapps2.activities.LoginActivity;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.Dashboard;
import com.example.riskyds.surveyapps2.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sevima on 5/28/2016.
 */
public class PasswordFragment extends Fragment{
    protected View v;

    protected EditText password;
    protected EditText password1;
    protected EditText password2;

    ProgressBar progres_bar;
    Button btnPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_password, container, false);
        password = ((EditText) v.findViewById(R.id.password));
        password1 = ((EditText) v.findViewById(R.id.password1));
        password2 = ((EditText) v.findViewById(R.id.password2));

        progres_bar = ((ProgressBar) v.findViewById(R.id.progress_bar));

        btnPassword =((Button) v.findViewById(R.id.btnPassword));
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = SessionManager.getInstance(getContext());
                Map<String, String> data = new HashMap<>();
                data.put("idakun", sessionManager.getThisUser().getIdakun());
                data.put("password", password.getText().toString());
                data.put("password1", password1.getText().toString());
                data.put("password2", password2.getText().toString());
                // request
                RequestAsyncTask login = new RequestAsyncTask(data, null, progres_bar) {
                    @Override
                    protected void setAfterThread(ResponseManager responseManager) {
                        if (responseManager.getCode().equals(Url.CodeTrue)) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        Toast.makeText(getActivity(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                login.execute(Url.password);
            }
        });
        return v;
    }
}
