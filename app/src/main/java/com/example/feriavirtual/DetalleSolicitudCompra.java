package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feriavirtual.API.Store.SolicitudCompra;

public class DetalleSolicitudCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud_compra);

        TextView descripcion = (TextView) findViewById(R.id.txtdetailSolicitudDescripcion);
        TextView nombreEstado = (TextView) findViewById(R.id.txtDetalleSolicitudNombreEstado);
        TextView precioVenta = (TextView) findViewById(R.id.txtDetalleSolicitudPrecioVenta);

        Bundle extras = getIntent().getExtras();
        SolicitudCompra solicitudCompra = extras.getParcelable(MenuClienteExterno.SELECTED_SOLICITUD_COMPRA);

        if(solicitudCompra != null){
            descripcion.setText(String.valueOf(solicitudCompra.getDescripcion()));
            precioVenta.setText(String.valueOf(solicitudCompra.getPrecioVenta()));
            nombreEstado.setText(String.valueOf(solicitudCompra.getNombreEstado()));
        }

        Button btnVolver = (Button) findViewById(R.id.btnDetalleSolicitudVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuClienteExterno.class);
                startActivity(intent);
            }
        });
    }
}