package com.example.feriavirtual.API;
import android.content.Context;
import android.util.Log;
import com.example.feriavirtual.API.Generales.Coneccion;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.ConfigBd;
import com.example.feriavirtual.BD.Consultas;
import com.example.feriavirtual.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginU implements Coneccion.DonwloadInterface{

    Context context;
    private String Rut;
    private String password;

    public String ConsultarUsuario(Context OrigenContext, String RutLogin, String PasswordLogin ){
        context = OrigenContext;
        Rut = RutLogin;
        password = PasswordLogin;
        Coneccion con = new Coneccion();
        con.delegate = this;
        con.execute();
       return null;
    }

    @Override
    public void onDownload(String data) {
        ArrayList<Usuario> listUser = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Log.d("dat2", jsonObject.toString());
            JSONObject JsonData = jsonObject.getJSONObject("infoToken");

            String Rut = JsonData.getString("rut");
            String Email = JsonData.getString("email");
            String Nombre = JsonData.getString("nombre");
            String Token = JsonData.getString("token");
            int RolId = JsonData.getInt("rolId");
            String NombreRol = JsonData.getString("nombreRol");

            listUser.add(new Usuario(Rut, Email, Nombre, Token, RolId, NombreRol));
            Log.d("datos",listUser.toString());
            Consultas consultas = new Consultas();
            consultas.AgregarUser(context, listUser.get(0));
            consultas.ObtenerUsuario(context);
        } catch (JSONException e) {
            Log.d("Error Get JSON", e.toString());
        }
    }
}
