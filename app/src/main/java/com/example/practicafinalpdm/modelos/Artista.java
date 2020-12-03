package com.example.practicafinalpdm.modelos;

import java.util.Date;

public class Artista {

    private String dniPasaporte, nombre, direccion, poblacion, provincia, pais, movilTrabajo, movilPersonal, telefonoFijo, email, webBlog;
    private Date fechaNacimiento;

    public Artista() {
    }

    public Artista(String dniPasaporte, String nombre, String direccion, String poblacion, String provincia, String pais, String movilTrabajo, String movilPersonal, String telefonoFijo, String email, String webBlog, Date fechaNacimiento) {
        this.dniPasaporte = dniPasaporte;
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.movilTrabajo = movilTrabajo;
        this.movilPersonal = movilPersonal;
        this.telefonoFijo = telefonoFijo;
        this.email = email;
        this.webBlog = webBlog;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMovilTrabajo() {
        return movilTrabajo;
    }

    public void setMovilTrabajo(String movilTrabajo) {
        this.movilTrabajo = movilTrabajo;
    }

    public String getMovilPersonal() {
        return movilPersonal;
    }

    public void setMovilPersonal(String movilPersonal) {
        this.movilPersonal = movilPersonal;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebBlog() {
        return webBlog;
    }

    public void setWebBlog(String webBlog) {
        this.webBlog = webBlog;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
