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

import com.example.feriavirtual.API.GetDataSolicitudCompra;
import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetalleSolicitudCompraValidacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_compra_validacion);

        TextView descripcion = (TextView) findViewById(R.id.txtdetailSolicitudDescripcion);
        TextView nombreEstado = (TextView) findViewById(R.id.txtDetalleSolicitudNombreEstado);
        TextView precioVenta = (TextView) findViewById(R.id.txtDetalleSolicitudPrecioVenta);

        Button btnVolver = (Button) findViewById(R.id.btnDetalleSolicitudVolver);
        Button btnNegarce = (Button) findViewById(R.id.btnNegado);
        Button btnAceptar = (Button) findViewById(R.id.btnAceptado);

        final GetDataSolicitudCompra updateSolicitudCompra = new GetDataSolicitudCompra();
        Consultas consulta = new Consultas();
        ArrayList<Usuario> listUser = consulta.ObtenerUsuario(getApplicationContext());
        final Usuario user = listUser.get(0);
        Bundle extras = getIntent().getExtras();
        final SolicitudCompra solicitudCompra = extras.getParcelable(MenuClienteExterno.SELECTED_SOLICITUD_COMPRA);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateSolicitudCompra.ActualizarSolicitudCompraAceptada(getApplicationContext(), user, solicitudCompra);
                    Toast.makeText(getApplicationContext(), "Solicitud aceptada", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



        if(solicitudCompra != null){
            descripcion.setText(String.valueOf(solicitudCompra.getDescripcion()));
            precioVenta.setText(String.valueOf(solicitudCompra.getPrecioVenta()));
            nombreEstado.setText(String.valueOf(solicitudCompra.getNombreEstado()));
        }


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuClienteExterno.class);
                startActivity(intent);
            }
        });

        btnNegarce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //EditText precioVentaActualizar = (EditText) findViewById(R.id.txtDetalleSolicitudPrecioVenta);
                //Log.d("ActualizarTexto", precioVentaActualizar.getText().toString());
                solicitudCompra.setPrecioVenta(0);

                try {
                    updateSolicitudCompra.ActualizarSolicitudCompra(getApplicationContext(), user, solicitudCompra);
                    Toast.makeText(getApplicationContext(), "Se rechaso solicitud de proceso de venta", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}