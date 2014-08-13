package com.vip.model;

import java.sql.Date;

/**
 * Created by hp on 13/08/2014.
 */
public class Mensaje {

    private Date fecha;
    private String comment;
    private Usuario usr;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
