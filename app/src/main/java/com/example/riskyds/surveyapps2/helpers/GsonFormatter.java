package com.example.riskyds.surveyapps2.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFormatter {

    public static Gson basic() {
        final Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.SYSTEM_DATETIME_STANDART)
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        return gson;
    }

    public static Gson dateLongFormat() {
        final Gson gson = new GsonBuilder()
                .setDateFormat(java.text.DateFormat.LONG)
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        return gson;
    }
}
