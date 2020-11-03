package com.example.feriavirtual.API.Store;

import android.content.Context;
import android.util.Log;

import com.example.feriavirtual.API.Generales.Coneccion;
import com.example.feriavirtual.BD.Consultas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GetDataSolicitudCompra implements Coneccion.DonwloadInterface{
    Context context;
    public ArrayList<Usuario> ConsutarSolicitudCompra(Context OrigenContext, String token ) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        //Ingresamos los datos del usuario.
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("UsuarioRut",  "25180038-3".toString());
        jsonObject.put("Descripcion", "Bearer");
        Log.d("Data de token", "antesAntes");
        Coneccion con = new Coneccion("POST", "ClienteExterno/AgregarSolicitudCompra",jsonObject);
        con.delegate = this;
        Log.d("Data de token", "antes");
        String RespuestaApi = con.execute().get();
        Log.d("Data de token", "despues");
        Log.d("Data de token", RespuestaApi);
        return  ObtenerDatosUsuarioJson(RespuestaApi);
    }

    @Override
    public void onDownload(String data) {

    }

    private ArrayList<Usuario> ObtenerDatosUsuarioJson(String dataJson){

        Log.d("Data de token", dataJson);
        ArrayList<Usuario> listUser = new ArrayList<>();
        try {
            //Validamos que el resultado no este vacio
            if(dataJson != null){
                JSONObject jsonObject = new JSONObject(dataJson);
                JSONObject JsonData = jsonObject.getJSONObject("infoToken");
            }
        } catch (JSONException e) {
            Log.d("Error Get JSON", e.toString());
        }
        return listUser;
    }
}
