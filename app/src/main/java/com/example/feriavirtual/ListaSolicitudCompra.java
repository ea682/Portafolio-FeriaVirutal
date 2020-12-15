package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.feriavirtual.API.GetDataSolicitudCompra;
import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;
import com.example.feriavirtual.Controller.SolicitudAdapter;

import java.util.ArrayList;

public class ListaSolicitudCompra extends AppCompatActivity {

    public final static String SELECTED_SOLICITUD_COMPRA = "Selected_Solicitud_Compra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_solicitud_compra);

        ListView listSolicitudesView = (ListView) findViewById(R.id.ListSolicitudesComprasFinalizar);

        Consultas con = new Consultas();

        ArrayList<Usuario> user = con.ObtenerUsuario(getApplicationContext());
        Usuario dataUser = user.get(0);
        try {
            GetDataSolicitudCompra data = new GetDataSolicitudCompra();
            ArrayList<SolicitudCompra> listaSolicitudes = data.ListaSolicitudesCompraPorFinalizar(getApplicationContext(), dataUser);
            final SolicitudAdapter solicitudAdapter = new SolicitudAdapter(this, R.layout.solicitudes_list_item, listaSolicitudes);
            listSolicitudesView.setAdapter(solicitudAdapter);

            listSolicitudesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SolicitudCompra selectedSolicitudCompra = solicitudAdapter.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), PagarSolicitudCompra.class);

                    intent.putExtra(SELECTED_SOLICITUD_COMPRA, (Parcelable) selectedSolicitudCompra);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}