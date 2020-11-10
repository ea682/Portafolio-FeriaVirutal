package com.example.feriavirtual.Controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.R;

import java.util.ArrayList;

public class SolicitudAdapter extends ArrayAdapter<SolicitudCompra> {
    private ArrayList<SolicitudCompra> solicitudList;
    private Context context;
    private int layoutId;

    public SolicitudAdapter(Context context, int resource, ArrayList<SolicitudCompra> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutId = resource;
        solicitudList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(layoutId, null);

        TextView txtDescripcionView = (TextView) rootView.findViewById(R.id.txtDescripcion);
        TextView txtValorView = (TextView) rootView.findViewById(R.id.txtValor);

        SolicitudCompra solicitudCompra =  solicitudList.get(position);

        txtDescripcionView.setText(solicitudCompra.getDescripcion());
        //En caso de ser un numero da un error
        txtValorView.setText(String.valueOf(solicitudCompra.getPrecioVenta()));
        return rootView;
    }
}
