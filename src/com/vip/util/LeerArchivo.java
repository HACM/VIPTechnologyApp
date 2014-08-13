package com.vip.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Frander Mejia
 */
public class LeerArchivo {
    private File archivo;
    private String path;
    private BufferedReader fileIn;

    public LeerArchivo(String _path) throws IOException{
        path = _path;
        archivo = new File(getPath());
        fileIn = new BufferedReader(new FileReader(getArchivo()));
    }

    public String Read() throws IOException{
        String cadena =fileIn.readLine();
        return cadena;
    }

    public void cerrar() throws IOException{
        fileIn.close();
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

    public BufferedReader getFileIn() {
        return fileIn;
    }

    public void setFileIn(BufferedReader fileIn) {
        this.fileIn = fileIn;
    }


}
