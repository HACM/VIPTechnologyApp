package com.vip.model;

/**
 * Created by hp on 04/08/2014.
 */
public class ItemNavigation {

    private String titulo;
    private int ico;

    public ItemNavigation(String _titulo, int _ico){
        titulo = _titulo;
        ico = _ico;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }
}
