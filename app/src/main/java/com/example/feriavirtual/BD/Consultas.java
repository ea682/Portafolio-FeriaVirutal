package com.example.feriavirtual.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.feriavirtual.API.Store.Usuario;

import java.util.ArrayList;

public class Consultas {

    private ConfigBd config;

    public void AgregarUser(Context context, Usuario User){
        config = new ConfigBd(context);
        SQLiteDatabase db = config.getWritableDatabase();
        Log.d("DUser", User.toString());
        if(db != null){
            try{
                String queryInsert = "INSERT INTO User VALUES ('"+User.getRut()+"','"+User.getEmail()+"','"+User.getNombre()+"','"+User.getToken()+"','"+User.getRolId()+"','"+User.getNombreRol()+"');";
                Log.d("query",queryInsert);
                db.execSQL(queryInsert);
            }catch (Exception ex){
                Log.d("Error Ingreso user BD", ex.toString());
            }
            db.close();
        }
    }

    public ArrayList<Usuario> ObtenerUsuario(Context context){
        ArrayList<Usuario> listUser = new ArrayList<>();
        config = new ConfigBd(context);
        SQLiteDatabase db = config.getReadableDatabase();
        if(db != null){
            try{

                Cursor cursorRes = db.query("User",null,null,null,null,null,null);
                while (cursorRes.moveToNext()){

                    String Rut = cursorRes.getString(0);
                    String Email = cursorRes.getString(1);
                    String Nombre = cursorRes.getString(2);
                    String Token = cursorRes.getString(3);
                    int RolId = cursorRes.getInt(4);
                    String NombreRol = cursorRes.getString(5);

                    listUser.add(new Usuario(Rut, Email, Nombre, Token, RolId, NombreRol));
                }

            }catch (Exception ex){
                Log.d("Error Ingreso user BD", ex.toString());
            }
            db.close();
        }
        return listUser;
    }
}
