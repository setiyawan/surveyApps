package com.example.riskyds.surveyapps2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.fragments.DashboadFragment;
import com.example.riskyds.surveyapps2.fragments.PasswordFragment;
import com.example.riskyds.surveyapps2.fragments.SurveyFragment;
import com.example.riskyds.surveyapps2.fragments.SurveyListFragment;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.User;

import java.io.InputStream;
import java.net.URL;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected FragmentManager fragmentManager;
    protected TextView txtAkun, txtInfo;
    protected ImageView img;
    protected Bitmap bitmap;
    protected ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().
                    add(R.id.flMain, new DashboadFragment()).
                    commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        SessionManager sessionManager = SessionManager.getInstance(this);
        img = ((ImageView) findViewById(R.id.imageView));
        txtAkun = (TextView) findViewById(R.id.txtAkun);
        txtInfo = (TextView) findViewById(R.id.txtInfo);

        txtAkun.setText(sessionManager.getThisUser().getNama());
        txtInfo.setText(sessionManager.getThisUser().getJabatan() + " | " + sessionManager.getThisUser().getNohp());
        new LoadImage().execute(Url.File + sessionManager.getThisUser().getFile());
        return true;
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            fragment = new DashboadFragment();
        } else if (id == R.id.nav_survey) {
            fragment = new SurveyListFragment();
        } else if (id == R.id.nav_change_password) {
            fragment = new PasswordFragment();
        } else if (id == R.id.nav_log_out) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.destroy();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.flMain, fragment).commit();
        }

        return true;
    }
}
