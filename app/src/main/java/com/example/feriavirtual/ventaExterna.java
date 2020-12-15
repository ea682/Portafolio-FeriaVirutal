package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feriavirtual.API.GetDataSolicitudCompra;
import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ventaExterna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_externa);
        Consultas consulta = new Consultas();
        ArrayList<Usuario> listUser = consulta.ObtenerUsuario(getApplicationContext());
        final Usuario user = listUser.get(0);

        final TextView nombreEstado = (TextView) findViewById(R.id.txtVentaExtermaNombreProducto);
        final TextView cantidad = (TextView) findViewById(R.id.txtVentaExtermaCantidad);
        final TextView descripcion = (TextView) findViewById(R.id.txtVentaExtermaDescripcion);
        Button btnVolver = (Button) findViewById(R.id.btnVentaExternaVolver);
        Button btnCrearVentaExterna = (Button) findViewById(R.id.btnVentaExternaCrear);

        Bundle extras = getIntent().getExtras();
        final SolicitudCompra solicitudCompra = extras.getParcelable(MenuClienteExterno.SELECTED_SOLICITUD_COMPRA);
        final GetDataSolicitudCompra crearSubasta = new GetDataSolicitudCompra();

        btnCrearVentaExterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("json subasta", "j1");
                    crearSubasta.CrearSubasta(getApplication(), user, solicitudCompra, nombreEstado.getText().toString(), cantidad.getText().toString(), descripcion.getText().toString());
                    Toast.makeText(getApplicationContext(), "Solicitud aceptada", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Upss, ocurrio un problema con su solicitud, intentelo mas tarde", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuClienteExterno.class);
                startActivity(intent);
            }
        });
    }
}