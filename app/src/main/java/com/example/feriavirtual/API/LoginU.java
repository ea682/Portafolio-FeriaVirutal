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
import java.util.concurrent.ExecutionException;

public class LoginU implements Coneccion.DonwloadInterface{

    Context context;

    public ArrayList<Usuario> ConsultarUsuario(Context OrigenContext, String RutLogin, String PasswordLogin ) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        //Ingresamos los datos del usuario.
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Rut", RutLogin);
        jsonObject.put("password", PasswordLogin);

        Coneccion con = new Coneccion("POST", "usuario/authenticate/true",jsonObject, "");
        con.delegate = this;
        String RespuestaApi = con.execute().get();

       return  ObtenerDatosUsuarioJson(RespuestaApi);
    }

    @Override
    public void onDownload(String data) {

    }

    private ArrayList<Usuario> ObtenerDatosUsuarioJson(String dataJson){
        ArrayList<Usuario> listUser = new ArrayList<>();
        try {
            //Validamos que el resultado no este vacio
            if(dataJson != null){
                JSONObject jsonObject = new JSONObject(dataJson);
                JSONObject JsonData = jsonObject.getJSONObject("infoToken");

                String Rut = JsonData.getString("rut");
                String Email = JsonData.getString("email");
                String Nombre = JsonData.getString("nombre");
                String Token = JsonData.getString("token");
                int RolId = JsonData.getInt("rolId");
                String NombreRol = JsonData.getString("nombreRol");

                listUser.add(new Usuario(Rut, Email, Nombre, Token, RolId, NombreRol));
                Consultas consultas = new Consultas();
                consultas.AgregarUser(context, listUser.get(0));
            }
        } catch (JSONException e) {
            Log.d("Error Get JSON", e.toString());
        }
        return listUser;
    }
}
