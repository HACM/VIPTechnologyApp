package com.example.VIPApp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vip.model.Mensaje;
import com.vip.model.Usuario;
import com.vip.util.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Date;

/**
 * Created by hp on 05/08/2014.
 */
public class MensajeActivity extends Activity {

    Mensaje mensaje = new Mensaje();
    Usuario usuario = new Usuario();
    private Toast toast;
    private ProgressDialog progDailog;

    File archivo;
    String path;
    private BufferedReader fileIn;

    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensaje);
        progDailog = new ProgressDialog(MensajeActivity.this);
        abrirArchivo();
    }

    public void abrirArchivo(){
        path = getApplicationContext().getFilesDir().getPath();
        String ruta = path + ConfigurationActivity.USERFILE;
        archivo = new File(ruta);
    }

    public void sendcomment(View view) throws IOException {

        EstablecerDatosDeVista();

        if (archivo.exists()){

            fileIn = new BufferedReader(new FileReader(archivo));
            String cadena = fileIn.readLine();

            if (!cadena.isEmpty()){

                Gson gson = new Gson();
                Type type = new TypeToken<Usuario>(){}.getType();
                usuario = gson.fromJson(cadena,type);

            }

        }

        mensaje.setUsr(usuario);
        mensaje.setFecha("12/12/2012");
        Gson gson = new Gson();
        String json = gson.toJson(mensaje);
        new Send().execute(json);
    }

    public void EstablecerDatosDeVista(){

        EditText msj = (EditText) findViewById(R.id.hola);
        String msj2 = msj.getText().toString();
        mensaje.setComment(msj2);

    }

    private class Send extends AsyncTask<String, Long, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                ConnectAndSend(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progDailog.setMessage("Enviando Comentario...");
            progDailog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void response) {
            progDailog.dismiss();
            toast = Toast.makeText(getApplicationContext(), "Mensaje Enviado Muchas Gracias :)", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void ConnectAndSend(String json) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost post = new HttpPost("http://192.168.43.55:8080/VIPBackEnd/comment/rest");
        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);

        HttpResponse resp = httpClient.execute(post);
        String respStr = EntityUtils.toString(resp.getEntity());

        Mensaje resmsj;

        Gson gson = new Gson();
        Type type = new TypeToken<Mensaje>(){}.getType();
        resmsj = gson.fromJson(respStr, type);
        System.out.println(respStr);
        System.out.println(resmsj);

        usuario.setId(resmsj.getUsr().getId());


        String usuraio = gson.toJson(usuario);
        String ruta = path + ConfigurationActivity.USERFILE;

        JsonUtil jsonUtil = new JsonUtil(ruta,usuraio);
        jsonUtil.guardar();


    }


}
