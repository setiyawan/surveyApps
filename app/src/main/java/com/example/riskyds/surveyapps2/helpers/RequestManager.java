package com.example.riskyds.surveyapps2.helpers;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.File;
import java.util.Map;


public class RequestManager {

    public static String build(String url) {
        Log.v("REQUEST", "Request URL " + url);

        String response = "";
        try {
//            HttpRequest request = HttpRequest.post(url);
            HttpRequest request = HttpRequest.get(url);
            response = request.body();
            Log.v("RESPONSE_BODY", "Response: " + response);
        } catch (HttpRequest.HttpRequestException e) {
            Log.e("RESPONSE_ERROR", "Error : " + e);
            return null;
        }

        return response;
    }

    public static String build(String url, Map<String, String> posts) {
        Log.v("REQUEST", "Request URL " + url + " - " + "params " + posts.toString());

        String response = "";
        try {
            HttpRequest request = HttpRequest.post(url);
            request.form(posts);
            response = request.body();
            Log.v("RESPONSE_BODY", "Response: " + response);
        } catch (HttpRequest.HttpRequestException e) {
            Log.e("RESPONSE_ERROR", "Error : " + e);
            return null;
        }

        return response;
    }

    public static String build(String url, Map<String, String> posts, Map<String, File> files) {
        Log.v("REQUEST", "Request URL " + url + " - " + "params " + posts.toString());

        String response = "";
        try {
            HttpRequest request = HttpRequest.post(url);
            for (Map.Entry<String, String> entry : posts.entrySet()) {
                request.part(entry.getKey(), entry.getValue());
                Log.v("CHECK_POST_PARAMS", entry.getKey() + "||" + entry.getValue());
            }
            for (Map.Entry<String, File> entry : files.entrySet()) {
                request.part(entry.getKey(), getFileName(String.valueOf(entry.getValue())), entry.getValue());
                Log.v("CHECK_FILE_PARAMS", entry.getKey() + "||" + getFileName(String.valueOf(entry.getValue())) + "||" + entry.getValue());
            }
            response = request.body();
            Log.v("RESPONSE_BODY", "Response: " + response);
        } catch (HttpRequest.HttpRequestException e) {
            Log.e("RESPONSE_ERROR", "Error : " + e);
            return null;
        }

        return response;
    }

    public static String getFileName(String path) {
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1, path.length());
    }

}