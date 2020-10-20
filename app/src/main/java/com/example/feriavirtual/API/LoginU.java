package com.example.feriavirtual.API;

import android.content.Context;
import android.util.Log;

import com.example.feriavirtual.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LoginU implements coneccion.DonwloadInterface{

    public String ConsultarUsuario(Context origen){
        coneccion con = new coneccion();
        con.delegate = this;
        con.execute();
       return "";
    }

    @Override
    public void onDownload(String data) {
        Log.d("Manzana", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray featuresJsonArray = jsonObject.getJSONArray("features");

            for (int i = 0; i < featuresJsonArray.length(); i++) {
                JSONObject featuresJsonObject = featuresJsonArray.getJSONObject(i);
                JSONObject propertiesJsonObject = featuresJsonObject.getJSONObject("properties");
                //double magnitude = propertiesJsonObject.getDouble("mag");
                //String place = propertiesJsonObject.getString("place");

                Log.d("Manzana", propertiesJsonObject.toString());
            }
        } catch (JSONException e) {

        }
    }
}
