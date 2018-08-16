package com.example.santiago_huh.drinks2u_v1.model;

public class Producto {
    private String titulo;
    private String titular;
    private String portada;

    public Producto(String titulo, String titular, String portada) {
        this.titulo = titulo;
        this.titular = titular;
        this.portada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
