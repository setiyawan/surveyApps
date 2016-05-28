package com.example.riskyds.surveyapps2.helpers;

import android.widget.EditText;

/**
 * Created by stef_ang on 8/13/2015.
 */
public class CheckEditText {

    public static boolean isEmpty(EditText editText, String errorMessage, boolean status) {
        int length = editText.getText().toString().length();
        if (length == 0) {
            editText.setError(errorMessage);
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            return true;
        }
        return status;
    }

    public static boolean isEqual(EditText editText1, EditText editText2, String errorMessage) {
        String str1 = editText1.getText().toString();
        String str2 = editText2.getText().toString();

        if (str1.equals(str2)) {
            return true;
        }
        editText1.setError(errorMessage);
        editText1.setFocusable(true);
        editText1.setFocusableInTouchMode(true);
        editText2.setError(errorMessage);
        editText2.setFocusable(true);
        editText2.setFocusableInTouchMode(true);
        return false;
    }
}
