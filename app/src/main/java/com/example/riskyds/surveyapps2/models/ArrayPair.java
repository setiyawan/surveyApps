package com.example.riskyds.surveyapps2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevima on 6/3/2016.
 */
public class ArrayPair implements Serializable {
    List<Pair> data = new ArrayList<>();

    public ArrayPair(List<Pair> data) {
        this.data = data;
    }

    public ArrayPair() {
    }

    public List<Pair> getData() {
        return data;
    }

    public String getValueFromKey(String key){
        String result = "-1";
        for (int i = 0; i<data.size();i++){
            if(key.equals(data.get(i).getKey())){
                result =  data.get(i).getValue();
            }
        }
        return result;
    }

    public String getKeyFromValue(String val){
        String result = "-1";
        for (int i = 0; i<data.size();i++){
            if(val.equals(data.get(i).getValue())){
                result =  data.get(i).getKey();
            }
        }
        return result;
    }
}
