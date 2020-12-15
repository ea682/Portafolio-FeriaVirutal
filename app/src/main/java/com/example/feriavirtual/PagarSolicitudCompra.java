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

import java.util.ArrayList;

public class PagarSolicitudCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_solicitud_compra);

        TextView descripcion = (TextView) findViewById(R.id.txtdetailSolicitudDescripcion);
        TextView nombreEstado = (TextView) findViewById(R.id.txtDetalleSolicitudNombreEstado);
        EditText precioVenta = (EditText) findViewById(R.id.txtDetalleSolicitudPrecioVenta);

        Button btnVolver = (Button) findViewById(R.id.btnDetalleSolicitudVolver);
        final Button btnEditarSolicitudCompra = (Button) findViewById(R.id.btnPagar);

        Bundle extras = getIntent().getExtras();
        final SolicitudCompra solicitudCompra = extras.getParcelable(MenuAdministrador.SELECTED_SOLICITUD_COMPRA);
        if (solicitudCompra != null) {
            descripcion.setText(String.valueOf(solicitudCompra.getDescripcion()));
            precioVenta.setText(String.valueOf(solicitudCompra.getPrecioVenta()));
            nombreEstado.setText(String.valueOf(solicitudCompra.getNombreEstado()));
        }


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuAdministrador.class);
                startActivity(intent);
            }
        });
        btnEditarSolicitudCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Consultas consulta = new Consultas();
                ArrayList<Usuario> listUser = consulta.ObtenerUsuario(getApplicationContext());
                Usuario user = listUser.get(0);

                EditText precioVentaActualizar = (EditText) findViewById(R.id.txtDetalleSolicitudPrecioVenta);
                Log.d("ActualizarTexto", precioVentaActualizar.getText().toString());
                solicitudCompra.setPrecioVenta(Integer.parseInt(precioVentaActualizar.getText().toString()));
                GetDataSolicitudCompra updateSolicitudCompra = new GetDataSolicitudCompra();
                try {
                    updateSolicitudCompra.PagarSolicitudCompra(getApplicationContext(), user, solicitudCompra);
                    Toast.makeText(getApplicationContext(), "El pago se realizo con exito", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}