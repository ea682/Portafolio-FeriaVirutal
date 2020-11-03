package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.feriavirtual.API.SolicitudCompra;
import com.example.feriavirtual.API.Store.GetDataSolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;
import com.example.feriavirtual.Controller.SolicitudAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuClienteExterno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente_externo);

        ListView listSolicitudesView = (ListView) findViewById(R.id.ListSolicitudes);

        Consultas con = new Consultas();
        GetDataSolicitudCompra data = new GetDataSolicitudCompra();
        ArrayList<Usuario> user = con.ObtenerUsuario(getApplicationContext());
        Usuario u = user.get(0);
        try {
            data.ConsutarSolicitudCompra(getApplicationContext(), u.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //SolicitudAdapter solicitudAdapter = new SolicitudAdapter(getApplicationContext(), R.layout.solicitudes_list_item, lista123 );
        //listSolicitudesView.setAdapter(solicitudAdapter);
    }
}