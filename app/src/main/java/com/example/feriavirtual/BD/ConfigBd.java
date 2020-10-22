package com.example.feriavirtual.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.feriavirtual.API.Store.Usuario;

import java.util.ArrayList;

public class ConfigBd extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "feriaVirutal.bd";
    private static final int VERSION_BD= 1;
    private static final String TABLA_USUARIO = "CREATE TABLE User (Rut TEXT PRIMARY KEY, Email TEXT, Nombre TEXT, Token TEXT, RolId INTEGER, NombreRol TEXT)";

    public ConfigBd(Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +NOMBRE_BD);
        onCreate(db);
    }
}
