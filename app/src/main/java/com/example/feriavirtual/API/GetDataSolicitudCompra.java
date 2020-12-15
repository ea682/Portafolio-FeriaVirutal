package com.example.feriavirtual.API;

import android.content.Context;
import android.util.Log;

import com.example.feriavirtual.API.Generales.Coneccion;
import com.example.feriavirtual.API.Store.SolicitudCompra;
import com.example.feriavirtual.API.Store.Usuario;
import com.example.feriavirtual.BD.Consultas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GetDataSolicitudCompra implements Coneccion.DonwloadInterface{
    Context context;
    public ArrayList<SolicitudCompra> ConsutarSolicitudCompra(Context OrigenContext, Usuario user ) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        Coneccion con = new Coneccion("GET", "ClienteExterno/BuscarSolicitudCompra/"+user.getRut(),jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  ObtenerDatosUsuarioJson(RespuestaApi);
    }

    public ArrayList<SolicitudCompra> ConsutarSolicitudCompraAdministrador(Context OrigenContext, Usuario user ) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        Coneccion con = new Coneccion("GET", "usuario/ListarSolicitudCompra",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  ObtenerDatosUsuarioJson(RespuestaApi);
    }

    public String AgregarSolicitudCompra(Context OrigenContext, Usuario user, SolicitudCompra solicitud) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UsuarioRut", solicitud.getUsuarioRut());
        jsonObject.put("Descripcion", solicitud.getDescripcion());
        //jsonObject.put("PrecioVenta", solicitud.getPrecioVenta());
        Coneccion con = new Coneccion("POST", "ClienteExterno/AgregarSolicitudCompra/",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        //Log.d("repusta new Solicitud", RespuestaApi);
        return  RespuestaApi;
    }

    public String ActualizarSolicitudCompra(Context OrigenContext, Usuario user, SolicitudCompra solicitud) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", solicitud.getId());
        jsonObject.put("PrecioVenta", solicitud.getPrecioVenta());
        //jsonObject.put("PrecioVenta", solicitud.getPrecioVenta());
        Coneccion con = new Coneccion("POST", "usuario/ActualizarPrecioSolicitudCompra/",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  RespuestaApi;
    }

    public String ActualizarSolicitudCompraAceptada(Context OrigenContext, Usuario user, SolicitudCompra solicitud) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Id", solicitud.getId());
        //jsonObject.put("PrecioVenta", solicitud.getPrecioVenta());
        Coneccion con = new Coneccion("POST", "ClienteExterno/ActualizarAceptacionPrecioVentaSolicitudCompra",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  RespuestaApi;
    }

    public String CrearSubasta(Context OrigenContext, Usuario user, SolicitudCompra solicitud, String nombreProducto, String cantidad, String descripcion) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("NombreProducto", nombreProducto);
        jsonObject.put("Cantidad", Integer.parseInt(cantidad));
        jsonObject.put("Descripcion", descripcion);
        jsonObject.put("SolicitudCompraId", solicitud.getId());
        Log.d("json subasta", jsonObject.toString());
        Coneccion con = new Coneccion("POST", "usuario/AgregarProcesoVentaExterna",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  RespuestaApi;
    }

    public ArrayList<SolicitudCompra> ListaSolicitudesCompraPorFinalizar(Context OrigenContext, Usuario user) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        Coneccion con = new Coneccion("GET", "ClienteExterno/ListarSolicitudCompraPorFinalizar",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  ObtenerDatosUsuarioJson(RespuestaApi);
    }

    public String PagarSolicitudCompra(Context OrigenContext, Usuario user, SolicitudCompra solicitud) throws JSONException, ExecutionException, InterruptedException {
        context = OrigenContext;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("IdSolicitudCompra", solicitud.getId());
        jsonObject.put("Monto", solicitud.getPrecioVenta());
        Log.d("json subasta", jsonObject.toString());
        Coneccion con = new Coneccion("POST", "ClienteExterno/PagarPedido",jsonObject, user.getToken());
        con.delegate = this;
        String RespuestaApi = con.execute().get();
        return  RespuestaApi;
    }

    @Override
    public void onDownload(String data) {

    }

    private ArrayList<SolicitudCompra> ObtenerDatosUsuarioJson(String dataJson){

        ArrayList<SolicitudCompra> listSolicitudCompra = new ArrayList<>();
        try {
            //Validamos que el resultado no este vacio
            if(dataJson != null){

                JSONArray jsonArray = new JSONArray(dataJson);
                //JSONObject jsonObject = new JSONObject(dataJson);
                int largoJsonArray = jsonArray.length();
                for (int i = 0; i<largoJsonArray; i++){

                    JSONObject JsonData = jsonArray.getJSONObject(i);
                    int id = JsonData.getInt("id");
                    String descripcion = JsonData.getString("descripcion");
                    int precioVenta = JsonData.getInt("precioVenta");
                    String usuarioRut = JsonData.getString("usuarioRut");
                    int estadoId = JsonData.getInt("estadoId");
                    String nombreEstado = JsonData.getString("nombreEstado");
                    listSolicitudCompra.add(new SolicitudCompra(id, descripcion, precioVenta, usuarioRut, estadoId, nombreEstado));
                }
            }
        } catch (JSONException e) {
            Log.d("Error Get JSON", e.toString());
        }
        return listSolicitudCompra;
    }
}
