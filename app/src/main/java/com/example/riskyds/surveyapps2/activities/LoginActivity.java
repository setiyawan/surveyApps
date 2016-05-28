package com.example.riskyds.surveyapps2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.User;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private ProgressBar login_progress;
    private Button buttonLogin;

    protected SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.txtUsername);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);
        login_progress = ((ProgressBar) findViewById(R.id.login_progress));

        sessionManager = SessionManager.getInstance(this);
        if (sessionManager.isUserLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
            startActivity(intent);
            finish();
        }

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new HashMap<>();
                data.put("username", editTextUsername.getText().toString());
                data.put("password", editTextPassword.getText().toString());
                // request
                RequestAsyncTask login = new RequestAsyncTask(data, null, login_progress) {
                    @Override
                    protected void setAfterThread(ResponseManager responseManager) {
                        if (responseManager.getCode().equals(Url.CodeTrue)) {
                            User user = ((User) responseManager.getOne(User.class));
                            sessionManager.init(user);
                            Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(getApplicationContext(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                login.execute(Url.Login);
            }
        });
    }
}
