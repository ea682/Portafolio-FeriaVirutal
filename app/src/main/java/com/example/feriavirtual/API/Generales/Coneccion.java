package com.example.feriavirtual.API.Generales;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class Coneccion extends AsyncTask<String, Void, String>{

   private String TipoPeticion;
    private String RutaPeticion;
    private JSONObject jsonPeticion;

    public Coneccion(String tipoPeticion, String rutaPeticion, JSONObject jsonPeticion) {
        this.TipoPeticion = tipoPeticion;
        this.RutaPeticion = rutaPeticion;
        this.jsonPeticion = jsonPeticion;
    }

    public String getTipoPeticion() {
        return TipoPeticion;
    }

    public String getRutaPeticion() {
        return RutaPeticion;
    }

    public JSONObject getJsonPeticion() {
        return jsonPeticion;
    }

    public DonwloadInterface delegate;
    public interface DonwloadInterface{
        void onDownload(String data);
    }


    public String testGet(URL urls){
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls.toString());

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                Log.d("ttt"  , Character.toString(current));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }
    public String GetConsultaData(URL urls) throws IOException {
        HttpURLConnection connection = null;

        try {
            Log.d("ruta", urls.toString());
            URL url=new URL(urls.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjI1MTgwMDM4LTMiLCJuYmYiOjE2MDQ0NDQzMzcsImV4cCI6MTYzNTk4MDMzNywiaWF0IjoxNjA0NDQ0MzM3LCJpc3MiOiIxMWY3NGVmNC1hZGMzLTQ5YjAtYTZmOS1mNGE0YWFkMGY4MzIifQ.i_ReSawrRPy-TexsjzLIeyA4CNORJLkPpV0iC3RjxkA");
            //OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            //streamWriter.write(getJsonPeticion().toString());
            //streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            Log.d("rest", String.valueOf(connection.getResponseCode()));
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                Log.d("test1", stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                Log.e("test2", connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            Log.e("test3", exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    public String PostConsultaData(URL urls){
        HttpURLConnection connection = null;

        try {
            URL url=new URL(urls.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjI1MTgwMDM4LTMiLCJuYmYiOjE2MDQ0NDQzMzcsImV4cCI6MTYzNTk4MDMzNywiaWF0IjoxNjA0NDQ0MzM3LCJpc3MiOiIxMWY3NGVmNC1hZGMzLTQ5YjAtYTZmOS1mNGE0YWFkMGY4MzIifQ.i_ReSawrRPy-TexsjzLIeyA4CNORJLkPpV0iC3RjxkA");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(getJsonPeticion().toString());
            Log.d("envio", getJsonPeticion().toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            Log.d("rest", String.valueOf(connection.getResponseCode()));
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                Log.d("test1", stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                Log.e("test2", connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            Log.e("test3", exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String data ="";
        try{
            if (getTipoPeticion().equals("POST")) {
                data = PostConsultaData(new URL(config.ruta+getRutaPeticion()));
            }else{
                if(getTipoPeticion().equals("GET")){
                    data = testGet(new URL(config.ruta+getRutaPeticion()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        delegate.onDownload(s);
    }
}