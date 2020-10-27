package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feriavirtual.API.LoginU;
import com.example.feriavirtual.API.Store.Usuario;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtRut = (EditText) findViewById(R.id.txtRut);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginU loginU = new LoginU();
                try {
                    ArrayList<Usuario> GetUser =  loginU.ConsultarUsuario(getApplicationContext(), txtRut.getText().toString(), txtPassword.getText().toString());
                    if(!GetUser.isEmpty()){
                        Usuario user = GetUser.get(0);
                        //Redirecionamos al layout o vistar que pertenece.
                                switch (user.getRolId()){
                                    case 1:
                                        intent = new Intent(Login.this, MenuAdministrador.class);
                                        break;
                                    case 2:
                                        intent = new Intent(Login.this, MenuAdministrador.class);
                                        break;
                                    case 3:
                                        intent = new Intent(Login.this, MenuAdministrador.class);
                                        break;
                                    case 4:
                                        intent = new Intent(Login.this, MenuAdministrador.class);
                                        break;
                                    case 5:
                                        intent = new Intent(Login.this, MenuAdministrador.class);
                                break;
                            case 6:
                                intent = new Intent(Login.this, MenuAdministrador.class);
                                break;
                            default:
                                //intent = new Intent(Login.this, Login.class);
                                Toast.makeText(getApplicationContext(), "Ups... ocurrio un problema, Intentelo mas tarde.", Toast.LENGTH_LONG);
                                break;
                        }
                        startActivity(intent);
                    }else{
                        Log.d("testErrorLogin", GetUser.toString());
                        Toast.makeText(getApplicationContext(), "Error en los datos.", Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}