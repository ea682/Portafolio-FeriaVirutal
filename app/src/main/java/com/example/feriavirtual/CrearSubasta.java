package com.example.feriavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feriavirtual.API.Store.SolicitudCompra;

public class CrearSubasta extends AppCompatActivity {

    public final static String SELECTED_SOLICITUD_COMPRA = "Selected_Solicitud_Compra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_subasta);

        TextView descripcion = (TextView) findViewById(R.id.txtDetalleSubastaDescripcion);
        TextView nombreEstado = (TextView) findViewById(R.id.txtDetalleSubastaEstado);
        TextView precioVenta = (TextView) findViewById(R.id.txtDetalleSubastaPrecioVenta);

        Button btnVolver = (Button) findViewById(R.id.btnCrearSubastaVolver);
        Button btnCrearSubasta = (Button) findViewById(R.id.btnCrearSubasta);
        Bundle extras = getIntent().getExtras();
        final SolicitudCompra solicitudCompra = extras.getParcelable(MenuAdministrador.SELECTED_SOLICITUD_COMPRA);

        if(solicitudCompra != null){
            descripcion.setText(String.valueOf(solicitudCompra.getDescripcion()));
            precioVenta.setText(String.valueOf(solicitudCompra.getPrecioVenta()));
            nombreEstado.setText(String.valueOf(solicitudCompra.getNombreEstado()));
        }

        btnCrearSubasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ventaExterna.class);
                intent.putExtra(SELECTED_SOLICITUD_COMPRA, (Parcelable) solicitudCompra);
                startActivity(intent);
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