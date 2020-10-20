package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import com.example.feriavirtual.API.*;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginU login = new LoginU();
        login.ConsultarUsuario(MainActivity.this);
    }
}