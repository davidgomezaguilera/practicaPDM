package com.example.practicafinalpdm.modelos;

import java.util.Date;

public class Exposicion {

    private String idExposicion, nombreExpo, descripcion;
    private Date fechaInicio, fechaFin;

    public Exposicion() {
    }

    public Exposicion(String idExposicion, String nombreExpo, String descripcion, Date fechaInicio, Date fechaFin) {
        this.idExposicion = idExposicion;
        this.nombreExpo = nombreExpo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(String idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getNombreExpo() {
        return nombreExpo;
    }

    public void setNombreExpo(String nombreExpo) {
        this.nombreExpo = nombreExpo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
