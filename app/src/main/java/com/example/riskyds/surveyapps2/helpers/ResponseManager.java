package com.example.riskyds.surveyapps2.helpers;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseManager {

    private String code;
    private String message;
    private String data;

    public ResponseManager(String response) {
        if (response != null) {
            try {
                JSONObject responseObj = new JSONObject(response);
                this.code = responseObj.getString("code");
                this.message = responseObj.getString("message");
                if (responseObj.get("data") != null) {
                    this.data = responseObj.get("data").toString();
                }
                Log.v("check_data", "code: " + code + "| message: " + message + "| data: " + data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.v("check_response", "response is null");
        }
    }

    public static ResponseManager getInstance(String response) {
        return new ResponseManager(response);
    }

    public String getData() {
        if (data != null && !data.equals("") && !data.equals("null"))
            return data;
        else
            return null;
    }

    public Object getOne(Class model) {
        if (getData() != null) {
            return GsonFormatter.basic().fromJson(getData(), model);
        } else {
            return null;
        }
    }

    public List<?> getMany(Class model) {
        List<Object> objectList = new ArrayList<>();
        if (getData() != null) {
            final JsonArray jsonElement = new JsonParser().parse(getData()).getAsJsonArray();
            for (JsonElement element : jsonElement) {
                objectList.add(GsonFormatter.basic().fromJson(element, model));
            }
        }
        return objectList;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
