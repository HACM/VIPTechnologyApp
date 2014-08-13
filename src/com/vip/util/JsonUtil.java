package com.vip.util;

import java.io.IOException;

/**
 * Created by hp on 13/08/2014.
 */
public class JsonUtil {
    private String path;
    private String cadena;

    public JsonUtil(String _path, String _cadena){
        path = _path;
        cadena = _cadena;
    }

    public void guardar() throws IOException {

        EscribirArchivo escribirArchivo = new EscribirArchivo(path);
        escribirArchivo.Escribir(cadena);
        escribirArchivo.cerrarAcrivo();

    }
}
