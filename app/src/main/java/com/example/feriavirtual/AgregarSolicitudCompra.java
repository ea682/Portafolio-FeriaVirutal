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

public class AgregarSolicitudCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_solicitud_compra);


        final TextView txtDescripcion = (TextView) findViewById(R.id.txtNuevaSolicitudDescripcion);
        final TextView txtPrecioVenta = (TextView) findViewById(R.id.txtNuevaSolicitudPrecioVenta);

        Button btnAgregarNuevaSolicitud = (Button) findViewById(R.id.btnAgregarSolicitud);
        Button btnVolverSolicitud = (Button) findViewById(R.id.btnVolverSolicitud);

        btnVolverSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getApplicationContext(), MenuClienteExterno.class);
                startActivity(inten);

            }
        });
        btnAgregarNuevaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consultas consulta = new Consultas();
                ArrayList<Usuario> listUser = consulta.ObtenerUsuario(getApplicationContext());
                Usuario user = listUser.get(0);
                SolicitudCompra newSolicitud = new SolicitudCompra(0, txtDescripcion.getText().toString(), Integer.parseInt(txtPrecioVenta.getText().toString()), user.getRut(), 0, "");
                GetDataSolicitudCompra solicitud = new GetDataSolicitudCompra();
                try {
                    String respuesta = solicitud.AgregarSolicitudCompra(getApplicationContext(), user, newSolicitud);

                    Log.d("respuestaMain", respuesta);
                    Toast.makeText(getApplicationContext(), "Se agrego la nueva solicitud", Toast.LENGTH_SHORT).show();
                    if(respuesta.equals("No existen registros.")){

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}