package com.example.practicafinalpdm.modelos;

public class Trabajo {

    private String nombreTrabajo, descripcion, tamanio, peso, dniArtista, foto;

    public Trabajo(String nombreTrabajo, String descripcion, String tamanio, String peso, String dniArtista, String foto) {
        this.nombreTrabajo = nombreTrabajo;
        this.descripcion = descripcion;
        this.tamanio = tamanio;
        this.peso = peso;
        this.dniArtista = dniArtista;
        this.foto = foto;
    }

    public Trabajo() {
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDniArtista() {
        return dniArtista;
    }

    public void setDniArtista(String dniArtista) {
        this.dniArtista = dniArtista;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
