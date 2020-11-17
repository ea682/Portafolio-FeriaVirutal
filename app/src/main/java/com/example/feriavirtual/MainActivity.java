package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feriavirtual.API.*;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Consultas consultasBd = new Consultas();

        //Obtenemos al usuario guardado, en caso contrario lo enviamos al login.
        ArrayList<Usuario> getUser = consultasBd.ObtenerUsuario(getApplicationContext());
        Intent intent = null;
        if(getUser.isEmpty()){
            intent = new Intent(MainActivity.this, Login.class);

        }else{
            Usuario user = getUser.get(0);
            Log.d("Administrador", String.valueOf(user.getRolId()));
            //Redirecionamos al layout o vista que pertenece.
            switch (user.getRolId()) {
                case 1:
                    intent = new Intent(MainActivity.this, MenuAdministrador.class);
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, MenuProductor.class);
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, MenuClienteExterno.class);
                    break;
                case 4:
                    intent = new Intent(MainActivity.this, MenuClienteInterno.class);
                    break;
                case 5:
                    intent = new Intent(MainActivity.this, MenuTramportista.class);
                    break;
                case 6:
                    intent = new Intent(MainActivity.this, MenuConsultor.class);
                    break;
                default:
                    //intent = new Intent(Login.this, Login.class);
                    Toast.makeText(getApplicationContext(), "Ups... ocurrio un problema, Intentelo mas tarde.", Toast.LENGTH_LONG);
                    break;
            }
        }

        try {
            startActivity(intent);
        }catch (Exception ex){

        }

    }
}