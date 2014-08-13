package com.vip.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Frander Mejia
 */

public class EscribirArchivo {

    private File archivo;
    private String path;
    PrintWriter fileOut = null;

    public EscribirArchivo(String _path) throws IOException{
        path = _path;
        archivo = new File(path);
        fileOut = new PrintWriter(new FileWriter(this.getArchivo()));
    }

    public void Escribir(String linea){
        fileOut.println(linea);
    }

    public void cerrarAcrivo(){
        fileOut.close();
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
