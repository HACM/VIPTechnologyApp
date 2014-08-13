package com.example.VIPApp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vip.model.Usuario;

import java.io.*;
import java.lang.reflect.Type;

/**
 * Created by hp on 13/08/2014.
 */
public class ConfigurationActivity extends Activity {

    Usuario usuario = new Usuario();
    File archivo;
    String path;
    PrintWriter fileOut = null;
    public static String USERFILE = "config_vip";
    private BufferedReader fileIn;
    Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        init();

        try {
            verificarDatos();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init(){

        path = getApplicationContext().getFilesDir().getPath();
        String ruta = path + USERFILE;
        archivo = new File(ruta);

    }

    public void verificarDatos() throws IOException {

        if (archivo.exists()){

            fileIn = new BufferedReader(new FileReader(archivo));
            String cadena = fileIn.readLine();

            if (!cadena.isEmpty()){

                Gson gson = new Gson();
                Type type = new TypeToken<Usuario>(){}.getType();
                usuario = gson.fromJson(cadena,type);
                EstablecerDatosDeVista();

            }

        }

    }

    //metodo llamado para guardar la info de la vista
    public void savedata(View view) throws IOException {
        ObtenerDatosDeVista();
        guardarDatos();
        toast = Toast.makeText(getApplicationContext(), "Datos guardado :)", Toast.LENGTH_LONG);
        toast.show();

    }

    public void EstablecerDatosDeVista(){

        EditText et = (EditText) findViewById(R.id.user);
        EditText et2 = (EditText) findViewById(R.id.phone);
        EditText et3 = (EditText) findViewById(R.id.empresa);
        EditText et4 = (EditText) findViewById(R.id.email);

        et.setText(usuario.getNombre().toCharArray(),0,usuario.getNombre().toCharArray().length);
        et2.setText(usuario.getTelefono().toCharArray(),0,usuario.getTelefono().toCharArray().length);
        et3.setText(usuario.getEmpresa().toCharArray(),0,usuario.getEmpresa().toCharArray().length);
        et4.setText(usuario.getEmail().toCharArray(),0,usuario.getEmail().toCharArray().length);
    }

    //Obtiene los datos de la vista y los guarda en un modelo
    public void ObtenerDatosDeVista() {
        TextView tx = (TextView) findViewById(R.id.user);
        TextView tx2 = (TextView) findViewById(R.id.empresa);
        TextView tx3 = (TextView) findViewById(R.id.phone);
        TextView tx4 = (TextView) findViewById(R.id.email);

        usuario.setNombre(tx.getText().toString());
        usuario.setEmpresa(tx2.getText().toString());
        usuario.setTelefono(tx3.getText().toString());
        usuario.setEmail(tx4.getText().toString());
    }

    //guarda los datos de configuración en un JSON
    public void guardarDatos() throws IOException {


        fileOut = new PrintWriter(new FileWriter(archivo));


        //outputStream = openFileOutput(USERFILE, getApplicationContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        //outputStream.write(json.getBytes());
        //outputStream.close();
        fileOut.println(json);
        fileOut.close();


    }

    public File getTempFile(Context context, String url) {
        File file = null;

        try {

            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, getApplicationContext().getCacheDir());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}