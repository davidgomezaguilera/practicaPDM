package com.example.practicafinalpdm.modelos;

public class Comentario {

    private String idExpo, nombreTrabajo, comentario;

    public String getIdExpo() {
        return idExpo;
    }

    public void setIdExpo(String idExpo) {
        this.idExpo = idExpo;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Comentario(String idExpo, String nombreTrabajo, String comentario) {
        this.idExpo = idExpo;
        this.nombreTrabajo = nombreTrabajo;
        this.comentario = comentario;
    }
}
