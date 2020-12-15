package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.feriavirtual.API.GetDataSolicitudCompra;
import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;
import com.example.feriavirtual.Controller.SolicitudAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuClienteExterno extends AppCompatActivity {

    public final static String SELECTED_SOLICITUD_COMPRA = "Selected_Solicitud_Compra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente_externo);

        ListView listSolicitudesView = (ListView) findViewById(R.id.ListSolicitudes);
        Button btnAgregarNuevaSolicitud = (Button) findViewById(R.id.btnAgregarSolicitud);
        Consultas con = new Consultas();

        ArrayList<Usuario> user = con.ObtenerUsuario(getApplicationContext());
        Usuario dataUser = user.get(0);
        try {
            GetDataSolicitudCompra data = new GetDataSolicitudCompra();
            ArrayList<SolicitudCompra> listaSolicitudes = data.ConsutarSolicitudCompra(getApplicationContext(), dataUser);
            final SolicitudAdapter solicitudAdapter = new SolicitudAdapter(this, R.layout.solicitudes_list_item, listaSolicitudes);
            listSolicitudesView.setAdapter(solicitudAdapter);

            listSolicitudesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SolicitudCompra selectedSolicitudCompra = solicitudAdapter.getItem(position);
                    //Log.d("Precio", String.valueOf(selectedSolicitudCompra.getPrecioVenta()));
                    Intent intent = null;
                    if(selectedSolicitudCompra.getEstadoId() == 2){

                        intent = new Intent(getApplicationContext(), DetalleSolicitudCompraValidacion.class);
                    }else{
                        intent = new Intent(getApplicationContext(), DetalleSolicitudCompra.class);
                    }

                    intent.putExtra(SELECTED_SOLICITUD_COMPRA, (Parcelable) selectedSolicitudCompra);
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnAgregarNuevaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgregarSolicitudCompra.class);
                startActivity(intent);
            }
        });
    }
}