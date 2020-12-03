package com.example.practicafinalpdm.modelos;

public class Exponen {

    private String idExpo;
    private String dniArtista;

    public Exponen(String idExpo, String dniArtista) {
        this.idExpo = idExpo;
        this.dniArtista = dniArtista;
    }

    public String getIdExpo() {
        return idExpo;
    }

    public void setIdExpo(String idExpo) {
        this.idExpo = idExpo;
    }

    public String getDniArtista() {
        return dniArtista;
    }

    public void setDniArtista(String dniArtista) {
        this.dniArtista = dniArtista;
    }
}
