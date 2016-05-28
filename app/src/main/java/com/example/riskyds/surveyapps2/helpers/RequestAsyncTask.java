package com.example.riskyds.surveyapps2.helpers;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.util.Map;

/**
 * Created by stef_ang on 12/24/2015.
 */
public abstract class RequestAsyncTask extends AsyncTask<String, Void, String> {

    private Map<String, String> arguments;
    private Map<String, File> files;
    private ProgressBar progressBar;

    public RequestAsyncTask(Map<String, String> arguments, Map<String, File> files, ProgressBar progressBar) {
        this.arguments = arguments;
        this.files = files;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (arguments != null && arguments.size() > 0 && files != null && files.size() > 0) {
            return RequestManager.build(params[0], arguments, files);
        } else if (arguments != null && arguments.size() > 0) {
            return RequestManager.build(params[0], arguments);
        } else {
            return RequestManager.build(params[0]);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ResponseManager responseManager = ResponseManager.getInstance(s);
        setAfterThread(responseManager);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    abstract protected void setAfterThread(ResponseManager responseManager);
}