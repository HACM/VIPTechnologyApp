package com.example.VIPApp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vip.model.Mensaje;
import com.vip.model.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;

/**
 * Created by hp on 05/08/2014.
 */
public class MensajeActivity extends Activity {

    Mensaje mensaje = new Mensaje();
    Usuario usuario = new Usuario();

    File archivo;
    String path;
    private BufferedReader fileIn;

    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensaje);

    }

    public void abrirArchivo(){
        path = getApplicationContext().getFilesDir().getPath();
        String ruta = path + ConfigurationActivity.USERFILE;
        archivo = new File(ruta);
    }

    public void sendcomment(View view) throws IOException {
        EditText msj = (EditText) findViewById(R.id.msj);
        mensaje.setComment(msj.getText().toString());
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
        //mensaje.setFecha();

    }


}
