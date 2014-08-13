package com.vip.model;

import java.sql.Date;

/**
 * Created by hp on 13/08/2014.
 */
public class Mensaje {

    private String fecha;
    private String comment;
    private Usuario usr;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }
}
